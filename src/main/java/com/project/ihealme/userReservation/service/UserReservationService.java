package com.project.ihealme.userReservation.service;

import com.project.ihealme.userReservation.domain.UserReservation;
import com.project.ihealme.userReservation.repository.UserReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class UserReservationService {

    @Autowired
    private UserReservationRepository userReservationRepository;

    public List<UserReservation> findReservations() {
        return userReservationRepository.findAll();
    }
}
