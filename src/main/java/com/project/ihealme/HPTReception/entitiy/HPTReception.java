package com.project.ihealme.HPTReception.entitiy;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name="HPTRECEPTION")
public class HPTReception {

    @Id @Column(name = "RESNO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long resNo;

    @Column(name = "REPEMAIL")
    private String repEmail;

    @Column(name = "PNAME")
    private String pName;

    @Column(name = "TXLIST")
    private String txList;

    @Column(name = "RDATE")
    private LocalDateTime rDate;

    @Column(name = "CURRENTSTATUS")
    private String currentStatus;

    @Column(name = "RTCOUNT")
    private Long RtCount;

}
