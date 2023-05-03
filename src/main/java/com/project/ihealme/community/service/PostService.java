package com.project.ihealme.community.service;

import com.project.ihealme.community.domain.User;
import com.project.ihealme.community.domain.Post;
import com.project.ihealme.community.dto.*;
import com.project.ihealme.community.repository.CommentRepository;
import com.project.ihealme.community.repository.PostRepository;
import com.project.ihealme.community.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public Long writePost(InsertPostRequestDTO insertPostRequestDTO) {
        User user = userRepository.findByUserEmail(insertPostRequestDTO.getUserEmail());

        Post post = insertPostRequestDTO.toEntity(user);
        Post savedPost = postRepository.save(post);

        return savedPost.getPostNo();
    }

    public PostPageResponseDTO getPostList(PostPageRequestDTO postPageRequestDTO) {
        Page<Object[]> result = null;

        String type = postPageRequestDTO.getType();
        Pageable pageable = postPageRequestDTO.getPageable(Sort.by("postNo").descending());

        if (type == null || type.equals("")) {
            result = postRepository.findPostWithDetails(pageable);
        } else {
            String keyword = postPageRequestDTO.getKeyword();

            switch (type) {
                case "h":
                    result = postRepository.findByHptNameContaining(keyword, pageable);
                    break;
                case "t":
                    result = postRepository.findByTitleContaining(keyword, pageable);
                    break;
                case "u":
                    result = postRepository.findByUserEmailContaining(keyword, pageable);
                    break;
            }
        }

        List<Integer> commentCountList = new ArrayList<>();

        result.get().forEach(row -> {
            Object[] arr = (Object[]) row;
            Post post = (Post) arr[0];

            Integer commentCount = commentRepository.countByPost(post);
            commentCountList.add(commentCount);
        });

        return new PostPageResponseDTO(result, commentCountList);
    }

    public PostResponseDTO getPost(Long postNo, Boolean addHitCount) {
        Object result = postRepository.findPostByPostNo(postNo);
        Object[] arr = (Object[]) result;
        Post post = (Post) arr[0];
        User user = (User) arr[1];

        if (addHitCount) {
            post.addHitCount();
        }
        Post savedPost = postRepository.save(post);
        Integer commentCount = commentRepository.countByPost(savedPost);

        PostResponseDTO postResponseDTO = new PostResponseDTO(savedPost, user, commentCount);

        return postResponseDTO;
    }


    @Transactional
    public void deleteWithReplies(Long postNo) {
        // comment 삭제
        postRepository.deleteById(postNo);
    }

    public void edit(EditPostRequestDTO editPostRequestDTO) {
        Post post = postRepository.findById(editPostRequestDTO.getPostNo()).get();
        post.changeTitle(editPostRequestDTO.getTitle());
        post.changeContent(editPostRequestDTO.getContent());

        postRepository.save(post);
    }

    public void addReport(Long postNo) {
        Post post = postRepository.findById(postNo).get();
        post.addReportCount();

        postRepository.save(post);
    }
}
