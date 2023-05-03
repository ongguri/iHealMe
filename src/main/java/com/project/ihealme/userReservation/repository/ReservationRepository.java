package com.project.ihealme.userReservation.repository;

import com.project.ihealme.userReservation.domain.UserReservation;

import java.util.List;

public interface ReservationRepository {

    List<UserReservation> findAll();
}
