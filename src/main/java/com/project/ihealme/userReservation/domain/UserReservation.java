package com.project.ihealme.userReservation.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name="USERRESERVATION")
public class UserReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RESNO_GEN")
    @SequenceGenerator(sequenceName = "USERRESERVATION_NO_SEQ", name = "RESNO_GEN", allocationSize = 1)
    @Column(name = "RESNO")
    private Long resNo;

    @Column(name = "USEREMAIL")
    private String userEmail;

    @Column(name = "HPTNAME")
    private String hptName;

    @Column(name = "TXLIST")
    private String txList;

    @Column(name = "RDATE")
    private LocalDateTime rDate;

    @Column(name = "CURRENTSTATUS")
    @ColumnDefault("'접수 대기'")
    private String currentStatus;
}
