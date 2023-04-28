package com.project.ihealme.HPTReception.entitiy;


import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.time.LocalDateTime;

@Entity
@Table(name = "HPTRECEPTION")
public class HPTReceptionDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    private String pname;

    private String txlist;

    private LocalDateTime rdate;

    private String status;
}
