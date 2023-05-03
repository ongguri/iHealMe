package com.project.ihealme.HPTReception.entitiy;

import javax.persistence.*;
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
