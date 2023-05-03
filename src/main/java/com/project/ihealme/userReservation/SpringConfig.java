package com.project.ihealme.userReservation;

import com.project.ihealme.userReservation.repository.UserReservationRepository;
import com.project.ihealme.userReservation.service.UserReservationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public UserReservationRepository userReservationRepository() {
        return new UserReservationRepository();
    }

    @Bean
    public UserReservationService userReservationService() {
        return new UserReservationService();
    }

}
