package com.project.ihealme.community.service;

import com.project.ihealme.community.domain.User;
import com.project.ihealme.community.domain.Post;
import com.project.ihealme.community.dto.*;
import com.project.ihealme.community.repository.PostRepository;
import com.project.ihealme.community.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public Long write(InsertPostRequestDTO insertPostRequestDTO) {
        User user = userRepository.findByUserEmail(insertPostRequestDTO.getUserEmail());

        Post post = insertPostRequestDTO.toEntity(user);
        Post savedPost = postRepository.save(post);

        return savedPost.getPostNo();
    }

    public PageResultDTO<PostResponseDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {

        Function<Object[], PostResponseDTO> fn
                = (en -> entityToDTO((Post) en[0], (User) en[1], 0)); //(int) en[2]

        Page<Object[]> result = null;

        String type = pageRequestDTO.getType();
        Pageable pageable = pageRequestDTO.getPageable(Sort.by("postNo").descending());

        if (type == null || type.equals("")) {
            result = postRepository.findPostWithUser(pageable);
        } else {
            String keyword = pageRequestDTO.getKeyword();

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

        return new PageResultDTO<>(result, fn);
    }

    public PostResponseDTO get(Long postNo) {
        Object result = postRepository.findPostByPostNo(postNo);
        Object[] arr = (Object[]) result;
        Post post = (Post) arr[0];

        post.addHitCount();
        Post savedPost = postRepository.save(post);

        PostResponseDTO postResponseDTO = entityToDTO(savedPost, (User) arr[1], 0); //(int) arr[2]

        return postResponseDTO;
    }

    private PostResponseDTO entityToDTO(Post post, User user, int commentCount) {
        PostResponseDTO postResponseDTO = post.toPostResponseDTO();
        postResponseDTO.setUserEmail(user.getUserEmail());
        postResponseDTO.setCommentCount(commentCount);
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
