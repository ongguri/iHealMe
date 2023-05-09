package com.project.ihealme.HptReception.domain;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name="HPTRECEPTION")
public class HptReception {

        @Id @Column(name = "RESNO", length = 20)
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        private int resNo;

        @Column(name = "EMAIL", length = 50)
        private String email;

        @Column(name = "PNAME", length = 20)
        private String pName;

        @Column(name = "TXLIST", length = 20)
        private String txList;

        @Column(name = "RDATE")
        @ColumnDefault("sysdate")
        private LocalDateTime rDate;

        @Column(name = "CURRENTSTATUS", length = 20)
        @ColumnDefault("'접수대기'")
        private String currentStatus;

        @Column(name = "RTCOUNT", length = 10)
        @ColumnDefault("0")
        private int rtCount;

}

