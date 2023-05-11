package com.project.ihealme.userReservation.service;

import com.project.ihealme.HptReception.domain.HptReception;
import com.project.ihealme.userReservation.domain.UserReservation;
import com.project.ihealme.userReservation.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

//@Transactional
@Transactional
@Service
public class UserReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public List<UserReservation> getUserReservationList() {
        return reservationRepository.findAll(Sort.by(Sort.Direction.DESC, "resNo"));
    }

//    public Long updateStatus(UserReservation userReservation) {
//
//        userReservation.setEmail("longlee@daum.net");
//        userReservation.setCurrentStatus("진료 전");
//        // test를 위해 값을 임의로 넣음. 원래대로라면 이메일은 로그인 정보를 가져옴
//
//        UserReservation userRes = reservationRepository.findByEmailAndCurrentStatus(
//                userReservation.getEmail(), userReservation.getCurrentStatus());
//
//        reservationRepository.save(userRes.toEntity(userRes));
//
//        return userRes.getResNo();
//    }

    public void updateCurrentStatus(int resNo, String newStatus, LocalDateTime rDate) {
        UserReservation userReservation = reservationRepository.findByResNo((long) resNo);

        userReservation.setCurrentStatus(newStatus);

        userReservation.setRegdate(rDate);

        reservationRepository.save(userReservation);
    }
}
