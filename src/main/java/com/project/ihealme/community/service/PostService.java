package com.project.ihealme.community.service;

import com.project.ihealme.community.domain.Post;
import com.project.ihealme.community.dto.*;
import com.project.ihealme.community.repository.CommentRepository;
import com.project.ihealme.community.repository.PostRepository;
import com.project.ihealme.user.entity.User;
import com.project.ihealme.user.repository.UserRepository;
import com.project.ihealme.userReservation.domain.UserReservation;
import com.project.ihealme.userReservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ReservationRepository userReservationRepository;

    @Transactional
    public Long writePost(PostWriteRequestDTO postWriteRequestDTO) {
        Long userId = postWriteRequestDTO.getUserId();
        Long resNo = postWriteRequestDTO.getResNo();

        User user = userRepository.findById(userId)
                .orElseThrow(()-> new IllegalArgumentException("해당 유저가 없습니다. userId =" + userId));

        UserReservation userReservation = userReservationRepository.findById(resNo)
                .orElseThrow(()-> new IllegalArgumentException("해당 접수가 없습니다. resNo =" + resNo));

        Post post = Post.create(postWriteRequestDTO, user, userReservation);
        Post savedPost = postRepository.save(post);

        return savedPost.getPostNo();
    }

    public PostPageResponseDTO getPostList(PostPageRequestDTO postPageRequestDTO) {
        Page<Post> result = null;

        String type = postPageRequestDTO.getType();
        Pageable pageable = postPageRequestDTO.getPageable(Sort.by("postNo").descending());

        if (type == null || type.equals("")) {
            result = postRepository.findAllByPage(pageable);
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
        Post post = postRepository.findByPostNo(postNo);

        if (addHitCount) {
            post.addHitCount();
        }

        PostResponseDTO postResponseDTO = new PostResponseDTO(post);

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
