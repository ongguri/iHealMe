package com.project.ihealme.community.service;

import com.project.ihealme.community.domain.Post;
import com.project.ihealme.community.domain.SearchType;
import com.project.ihealme.community.dto.*;
import com.project.ihealme.community.exception.ExceptionType;
import com.project.ihealme.community.repository.CommentRepository;
import com.project.ihealme.community.repository.PostRepository;
import com.project.ihealme.user.entity.User;
import com.project.ihealme.user.entity.UserRole;
import com.project.ihealme.user.repository.UserRepository;
import com.project.ihealme.userReservation.domain.UserReservation;
import com.project.ihealme.userReservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
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
        //비로그인 회원 -> 로그인 필요
        if (user == null) {
            throw new IllegalArgumentException(ExceptionType.USER_NOT_LOGIN.getMessage());
        }
        //로그인 회원 -> 병원 유저는 글 작성 불가
        if (user.getUserRole() == UserRole.HOSPITAL) {
            throw new IllegalArgumentException(ExceptionType.POST_WRITE_NOT_ALLOWED.getMessage());
        }
        //로그인 회원 -> 포스트 작성을 할 수 있는 유저가 아니면 글 작성 불가
        if (!user.getUserId().equals(postWriteRequestDTO.getUserId())) {
            throw new IllegalArgumentException(ExceptionType.POST_WRITE_NOT_ALLOWED.getMessage());
        }

        Long resNo = postWriteRequestDTO.getResNo();
        //존재하지 않는 접수내역에 대한 후기 작성 불가
        UserReservation userReservation = reservationRepository.findById(resNo)
                .orElseThrow(()-> new IllegalArgumentException(ExceptionType.USER_RESERVATION_NOT_FOUND.getMessage()));

        Post post = Post.create(postWriteRequestDTO, user, userReservation);
        Post savedPost = postRepository.save(post);
        if (post.getPostNo().equals(savedPost.getPostNo())) {
            userReservation.setCurrentStatus("후기작성완료");
        }

        return savedPost.getPostNo();
    }

    public PostPageResponseDTO getPostList(PostPageRequestDTO postPageRequestDTO) {
        String type = postPageRequestDTO.getType();
        Pageable pageable = postPageRequestDTO.getPageable(Sort.by("postNo").descending());

        if (type == null || type.equals("")) {
            return new PostPageResponseDTO(postRepository.findAllByPage(pageable));
        } else {
            String keyword = postPageRequestDTO.getKeyword();

            if (type.equals(SearchType.HOSPITAL_NAME.getType())) {
                    return new PostPageResponseDTO(postRepository.findByHptNameContaining(keyword, pageable));
            }
            if (type.equals(SearchType.TITLE.getType())) {
                    return new PostPageResponseDTO(postRepository.findByTitleContaining(keyword, pageable));
            }
            if (type.equals(SearchType.WRITER.getType())) {
                    return new PostPageResponseDTO(postRepository.findByUserEmailContaining(keyword, pageable));
            }

            throw new IllegalArgumentException(ExceptionType.INVALID_SEARCH_TYPE.getMessage());
        }
    }

    public PostResponseDTO getPost(Long postNo, boolean addHitCount) {
        Post post = postRepository.findByPostNo(postNo)
                .orElseThrow(()-> new IllegalArgumentException(ExceptionType.POST_NOT_FOUND.getMessage()));

        return new PostResponseDTO(post, addHitCount ? post.getHit() + 1 : post.getHit());
    }

    public PostResponseDTO getPostEditForm(Long postNo, User user) {
        validateWriter(postNo, user);

        return getPost(postNo, false);
    }

    @Transactional
    public void deleteWithReplies(Long postNo, User user) {
        validateWriter(postNo, user);

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

        validateWriter(postNo, user);

        Post post = postRepository.findByPostNo(postNo).get();
        post.edit(postEditRequestDTO.getTitle(), postEditRequestDTO.getContent());
    }

    @Transactional
    public void addReport(Long postNo, User user) {
        if (user == null) {
            throw new IllegalArgumentException(ExceptionType.USER_NOT_LOGIN.getMessage());
        }

        if (checkWriter(postNo, user)) { return; }

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

    public boolean checkWriter(Long postNo, User user) {
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

    private void validateWriter(Long postNo, User user) {
        boolean isUserWriterOfPost = checkWriter(postNo, user);

        if (!isUserWriterOfPost) {
            throw new IllegalArgumentException(ExceptionType.USER_NOT_MATCH_POST_WRITER.getMessage());
        }
    }
}
