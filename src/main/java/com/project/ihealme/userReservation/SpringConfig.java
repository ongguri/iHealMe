package com.project.ihealme.userReservation;

import com.project.ihealme.userReservation.repository.UserReservationRepository;
import com.project.ihealme.userReservation.service.UserReservationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class SpringConfig {
    private final EntityManager em;

    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public UserReservationService userReservationService() {
        return new UserReservationService();
    }

    @Bean
    public UserReservationRepository userReservationRepository() {
        return new UserReservationRepository(em);
    }
}
