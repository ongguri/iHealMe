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

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long writePost(PostWriteRequestDTO postWriteRequestDTO) {
        Long userId = postWriteRequestDTO.getUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new IllegalArgumentException("해당 유저가 없습니다. userId =" + userId));

        Post post = postWriteRequestDTO.toEntity(user);
        Post savedPost = postRepository.save(post);

        return savedPost.getPostNo();
    }

    @Transactional
    public PostPageResponseDTO getPostList(PostPageRequestDTO postPageRequestDTO) {
        Page<Object[]> result = null;

        String type = postPageRequestDTO.getType();
        Pageable pageable = postPageRequestDTO.getPageable(Sort.by("postNo").descending());

        if (type == null || type.equals("")) {
            result = postRepository.findPostAndUserByPage(pageable);
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

        return new PostPageResponseDTO(result);
    }

    public PostResponseDTO getPost(Long postNo) {
        return getPost(postNo, false);
    }

    @Transactional
    public PostResponseDTO getPost(Long postNo, boolean addHitCount) {
        List<Object[]> results = postRepository.findPostAndUserByPostNo(postNo);

        Post post = null;
        User user = null;

        for (Object[] result : results) {
            post = (Post) result[0];
            user = (User) result[1];
        }

        if (addHitCount) {
            post.addHitCount();
        }

        PostResponseDTO postResponseDTO = new PostResponseDTO(post, user, post.getComments().size());

        return postResponseDTO;
    }

    @Transactional
    public void deleteWithReplies(Long postNo) {
        // comment 삭제
        postRepository.deleteById(postNo);
    }

    @Transactional
    public void edit(PostEditRequestDTO postEditRequestDTO) {
        Long postNo = postEditRequestDTO.getPostNo();
        Post post = postRepository.findById(postNo)
                .orElseThrow(()-> new IllegalArgumentException(postNo + "번 게시글이 없습니다."));

        post.edit(postEditRequestDTO.getTitle(), postEditRequestDTO.getContent());
    }

    public Post addReport(Long postNo) {
        Post post = postRepository.findById(postNo).get();
        post.addReportCount();

        Post savedPost = postRepository.save(post);

        return savedPost;
    }
}
