package com.project.ihealme.userReservation.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class UserReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resNo")
    private Long resNo;

    @Column(name = "userEmail")
    private String userEmail;

    @Column(name = "hptName")
    private String hptName;

    @Column(name = "txList")
    private String txList;

    @Column(name = "rDate")
    private LocalDateTime rDate;

    @Column(name = "currentStatus")
    private String currentStatus;
}