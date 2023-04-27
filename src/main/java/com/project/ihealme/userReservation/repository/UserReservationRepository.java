package com.project.ihealme.userReservation.repository;

import com.project.ihealme.userReservation.domain.UserReservation;

import javax.persistence.EntityManager;
import java.util.List;

public class UserReservationRepository {

    private final EntityManager em;

    public UserReservationRepository(EntityManager em) {
        this.em = em;
    }

    public List<UserReservation> findAll() {
        return em.createQuery("SELECT u FROM UserReservation u", UserReservation.class).getResultList();
    }
}
