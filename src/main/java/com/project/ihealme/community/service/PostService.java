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
    private final ReservationRepository reservationRepository;

    @Transactional
    public Long writePost(PostWriteRequestDTO postWriteRequestDTO) {
        Long userId = postWriteRequestDTO.getUserId();
        Long resNo = postWriteRequestDTO.getResNo();

        User user = userRepository.findById(userId)
                .orElseThrow(()-> new IllegalArgumentException("해당 유저가 없습니다. userId =" + userId));

        UserReservation userReservation = reservationRepository.findById(resNo)
                .orElseThrow(()-> new IllegalArgumentException("해당 접수가 없습니다. resNo =" + resNo));

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

    public PostResponseDTO getPost(Long postNo) {
        return getPost(postNo, false);
    }

    public PostResponseDTO getPost(Long postNo, boolean addHitCount) {
        Post post = postRepository.findByPostNo(postNo)
                .orElseThrow(()-> new IllegalArgumentException(postNo + "번 게시글이 없습니다."));

        return new PostResponseDTO(post, addHitCount ? post.getHit() + 1 : post.getHit());
    }

    @Transactional
    public void deleteWithReplies(Long postNo) {
        System.out.println("commentRepository.deleteByPostNo(postNo)");
        commentRepository.deleteByPostNo(postNo);
        System.out.println("postRepository.deleteById(postNo)");
        postRepository.deleteById(postNo);
    }

    @Transactional
    public void edit(PostEditRequestDTO postEditRequestDTO) {
        Long postNo = postEditRequestDTO.getPostNo();
        Post post = postRepository.findByPostNo(postNo)
                .orElseThrow(()-> new IllegalArgumentException(postNo + "번 게시글이 없습니다."));

        post.edit(postEditRequestDTO.getTitle(), postEditRequestDTO.getContent());
    }

    @Transactional
    public void addReport(Long postNo) {
        Post post = postRepository.findByPostNo(postNo)
                .orElseThrow(()-> new IllegalArgumentException(postNo + "번 게시글이 없습니다."));

        int updatedPost = postRepository.updateReport(post.getPostNo());
        // 예외 던지기
    }

    @Transactional
    public void addHitCount(Long postNo) {
        Post post = postRepository.findByPostNo(postNo)
                .orElseThrow(()-> new IllegalArgumentException(postNo + "번 게시글이 없습니다."));

        int updatedPost = postRepository.updateHit(postNo);
        if (updatedPost != 1) {
            throw new IllegalArgumentException(postNo + "번 게시글의 조회수를 수정하지 못했습니다.");
        }
    }
}
