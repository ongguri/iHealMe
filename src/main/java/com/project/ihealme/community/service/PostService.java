package com.project.ihealme.community.service;

import com.project.ihealme.community.domain.Post;
import com.project.ihealme.community.domain.SearchType;
import com.project.ihealme.community.dto.*;
import com.project.ihealme.community.repository.CommentRepository;
import com.project.ihealme.community.repository.PostRepository;
import com.project.ihealme.user.entity.User;
import com.project.ihealme.user.entity.UserRole;
import com.project.ihealme.userReservation.domain.UserReservation;
import com.project.ihealme.userReservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final ReservationRepository reservationRepository;

    @Transactional
    public Long writePost(User user, PostWriteRequestDTO postWriteRequestDTO) {
        //비로그인 회원 -> 로그인 필요
        validateUserLogin(user);

        //로그인 회원 -> 병원 유저는 글 작성 불가
        //          -> 해당 접수의 후기 작성을 할 수 있는 유저가 아니면 작성 불가
        if (user.getUserRole() == UserRole.HOSPITAL ||
                !user.getUserId().equals(postWriteRequestDTO.getUserId())) {

            throw new IllegalArgumentException(ExceptionType.POST_WRITE_NOT_ALLOWED.getMessage());
        }

        //존재하지 않는 접수내역에 대한 후기 작성 불가
        Long resNo = postWriteRequestDTO.getResNo();
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

    @Transactional
    public PostResponseDTO getPost(Long postNo, boolean addHitCount) {
        Post post = validateFindPost(postNo);
        if (addHitCount) addHitCount(post.getPostNo());

        return new PostResponseDTO(post, addHitCount ? post.getHit() + 1 : post.getHit());
    }

    private void addHitCount(Long postNo) {
        validateFindPost(postNo);

        int updatedPost = postRepository.updateHit(postNo);
        if (updatedPost != 1) {
            throw new IllegalArgumentException(ExceptionType.EXCEPTION.getMessage());
        }
    }

    public PostResponseDTO getPostEditForm(Long postNo, User user) {
        validateWriterOfPost(postNo, user);
        return getPost(postNo, false);
    }

    @Transactional
    public void edit(Long postNo, User user, PostEditRequestDTO postEditRequestDTO) {
        if (!postNo.equals(postEditRequestDTO.getPostNo())) {
            throw new IllegalArgumentException(ExceptionType.POST_EDIT_NOT_ALLOWED.getMessage());
        }

        validateWriterOfPost(postNo, user);

        Post post = postRepository.findByPostNo(postNo).get();
        post.edit(postEditRequestDTO.getTitle(), postEditRequestDTO.getContent());
    }

    @Transactional
    public void deleteWithReplies(Long postNo, User user) {
        validateWriterOfPost(postNo, user);

        commentRepository.deleteByPostNo(postNo);
        postRepository.deleteById(postNo);
    }

    @Transactional
    public ResponseEntity<String> addReport(Long postNo, User user, boolean addReportCount) {
        validateUserLogin(user);

        if (!addReportCount)
            return new ResponseEntity<>("이미 신고한 게시글입니다.", HttpStatus.OK);

        if (isWriterOfPost(postNo, user))
            return new ResponseEntity<>("글 작성자는 신고할 수 없습니다.", HttpStatus.OK);

        Post post = postRepository.findByPostNo(postNo).get();
        int updatedPost = postRepository.updateReport(post.getPostNo());
        if (updatedPost != 1)
            throw new IllegalArgumentException(ExceptionType.POST_REPORT_NOT_ALLOWED.getMessage());

        return new ResponseEntity<>("게시글을 신고하였습니다.", HttpStatus.CREATED);
    }

    public boolean isWriterOfPost(Long postNo, User user) {
        if (!isLoginUser(user)) return false;

        Post post = validateFindPost(postNo);
        return post.getUser().getUserId().equals(user.getUserId());
    }

    private boolean isLoginUser(User user) {
        return user != null;
    }

    private void validateUserLogin(User user) {
        if (!isLoginUser(user)) {
            throw new IllegalArgumentException(ExceptionType.USER_NOT_LOGIN.getMessage());
        }
    }

    private Post validateFindPost(Long postNo) {
        return postRepository.findByPostNo(postNo)
                .orElseThrow(()-> new IllegalArgumentException(ExceptionType.POST_NOT_FOUND.getMessage()));
    }

    private void validateWriterOfPost(Long postNo, User user) {
        if (!isWriterOfPost(postNo, user)) {
            throw new IllegalArgumentException(ExceptionType.USER_NOT_MATCH_POST_WRITER.getMessage());
        }
    }
}
