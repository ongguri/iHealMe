package com.project.ihealme.userReservation.service;

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
//
//    public UserReservation getUserReservationId() {
//        return reservationRepository.findById()
//    }

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
