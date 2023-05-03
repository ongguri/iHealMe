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
}
