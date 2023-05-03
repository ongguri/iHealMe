package com.project.ihealme.HPTReception.entitiy;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "HPTRECEPTION")
public class HPTReceptionDo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NO_GEN")
    @SequenceGenerator(sequenceName = "HPTRESERVATION_NO_SEQ", name = "NO_GEN", allocationSize = 1)
    private Long no;

    private String pname;

    private String txlist;

    private LocalDateTime rdate;

    private String status;
}
