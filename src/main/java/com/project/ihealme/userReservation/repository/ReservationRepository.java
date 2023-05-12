package com.project.ihealme.userReservation.repository;

import com.project.ihealme.userReservation.domain.UserReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<UserReservation, Long> {

    UserReservation findByEmailAndCurrentStatus(String userEmail, String currentStatus);
    UserReservation findByResNo(Long resNo);
}
