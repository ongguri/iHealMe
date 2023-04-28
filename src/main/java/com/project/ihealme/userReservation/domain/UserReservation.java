package com.project.ihealme.userReservation.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class UserReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resNo;
    private String userEmail;
    private String hptName;
    private String txList;
    private LocalDateTime rDate;
    private String status;
}
