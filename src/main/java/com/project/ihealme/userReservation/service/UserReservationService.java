package com.project.ihealme.userReservation.service;

import com.project.ihealme.community.domain.User;
import com.project.ihealme.userReservation.domain.UserReservation;
import com.project.ihealme.userReservation.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@Transactional
@Transactional
public class UserReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public List<UserReservation> getUserReservationList() {
        return reservationRepository.findAll();
    }

    public Long updateStatus(UserReservation userReservation) {

        userReservation.setUserEmail("longlee@daum.net");
        userReservation.setCurrentStatus("진료 전");
        // test를 위해 값을 임의로 넣음. 원래대로라면 이메일은 로그인 정보를 가져옴

        UserReservation userRes = reservationRepository.findByUserEmailAndCurrentStatus(
                userReservation.getUserEmail(), userReservation.getCurrentStatus());

        reservationRepository.save(userRes.toEntity(userRes));

        return userRes.getResNo();
    }

//    public Long writePost(InsertPostRequestDTO insertPostRequestDTO) {
//        User user = userRepository.findByUserEmail(insertPostRequestDTO.getUserEmail());
//
//        Post post = insertPostRequestDTO.toEntity(user);
//        Post savedPost = postRepository.save(post);
//
//        return savedPost.getPostNo();
//    }
//
//    public Post toEntity(User user) {
//        Post post = Post.builder()
//                .resNo(resNo)
//                .user(user)
//                .hptName(hptName)
//                .title(title)
//                .content(content)
//                .build();
//
//        return post;
//    }
}
