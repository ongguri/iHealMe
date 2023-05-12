package com.project.ihealme.community.service;

import com.project.ihealme.community.domain.Post;
import com.project.ihealme.community.dto.*;
import com.project.ihealme.community.exception.ExceptionType;
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
    private final ReservationRepository reservationRepository;

    @Transactional
    public Long writePost(User user, PostWriteRequestDTO postWriteRequestDTO) {
        if (user == null) {

        }
        userRepository.findById(user.getUserId())
                .orElseThrow(()-> new IllegalArgumentException(ExceptionType.USER_NOT_FOUND.getMessage()));

        if (user.getUserId() != postWriteRequestDTO.getUserId()) {
            throw new IllegalArgumentException(ExceptionType.POST_WRITE_NOT_ALLOWED.getMessage());
        }

        Long resNo = postWriteRequestDTO.getResNo();
        UserReservation userReservation = reservationRepository.findById(resNo)
                .orElseThrow(()-> new IllegalArgumentException(ExceptionType.USER_RESERVATION_NOT_FOUND.getMessage()));

        Post post = Post.create(postWriteRequestDTO, user, userReservation);

        Post savedPost = postRepository.save(post);
        userReservation.setCurrentStatus("후기작성완료");

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

    public PostResponseDTO getPost(Long postNo, boolean addHitCount) {
        Post post = postRepository.findByPostNo(postNo)
                .orElseThrow(()-> new IllegalArgumentException(ExceptionType.POST_NOT_FOUND.getMessage()));

        return new PostResponseDTO(post, addHitCount ? post.getHit() + 1 : post.getHit());
    }

    public PostResponseDTO getPostEditForm(Long postNo, User user) {
        validateUserOfPost(postNo, user);

        return getPost(postNo, false);
    }

    @Transactional
    public void deleteWithReplies(Long postNo, User user) {
        validateUserOfPost(postNo, user);

        commentRepository.deleteByPostNo(postNo);
        System.out.println("postRepository.deleteById(postNo) 시작");
        postRepository.deleteById(postNo);
        System.out.println("postRepository.deleteById(postNo) 끝");
    }

    @Transactional
    public void edit(Long postNo, User user, PostEditRequestDTO postEditRequestDTO) {
        if (postNo != postEditRequestDTO.getPostNo()) {
            throw new IllegalArgumentException(ExceptionType.POST_EDIT_NOT_ALLOWED.getMessage());
        }

        validateUserOfPost(postNo, user);

        Post post = postRepository.findByPostNo(postNo).get();
        post.edit(postEditRequestDTO.getTitle(), postEditRequestDTO.getContent());
    }

    @Transactional
    public void addReport(Long postNo, User user) {
        if (user == null) {
            throw new IllegalArgumentException(ExceptionType.USER_NOT_LOGIN.getMessage());
        }

        if (checkUserOfPost(postNo, user)) { return; }

        Post post = postRepository.findByPostNo(postNo).get();
        int updatedPost = postRepository.updateReport(post.getPostNo());
        if (updatedPost != 1) {
            throw new IllegalArgumentException(ExceptionType.POST_REPORT_NOT_ALLOWED.getMessage());
        }
    }

    @Transactional
    public void addHitCount(Long postNo) {
        Post post = postRepository.findByPostNo(postNo)
                .orElseThrow(()-> new IllegalArgumentException(ExceptionType.POST_NOT_FOUND.getMessage()));

        int updatedPost = postRepository.updateHit(postNo);
        if (updatedPost != 1) {
            throw new IllegalArgumentException(ExceptionType.POST_EDIT_NOT_ALLOWED.getMessage());
        }
    }

    public boolean checkUserOfPost(Long postNo, User user) {
        if (!isLoginUser(user)) {
            return false;
        }

        userRepository.findById(user.getUserId())
                .orElseThrow(()-> new IllegalArgumentException(ExceptionType.USER_NOT_FOUND.getMessage()));

        Post post = postRepository.findByPostNo(postNo)
                .orElseThrow(()-> new IllegalArgumentException(ExceptionType.POST_NOT_FOUND.getMessage()));

        return post.getUser().getUserId().equals(user.getUserId());
    }

    private boolean isLoginUser(User user) {
        return user != null;
    }

    private void validateUserOfPost(Long postNo, User user) {
        boolean isUserWriterOfPost = checkUserOfPost(postNo, user);

        if (!isUserWriterOfPost) {
            throw new IllegalArgumentException(ExceptionType.USER_NOT_MATCH_POST_WRITER.getMessage());
        }
    }
}
