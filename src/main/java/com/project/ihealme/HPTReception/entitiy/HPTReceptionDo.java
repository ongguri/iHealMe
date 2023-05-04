package com.project.ihealme.HPTReception.entitiy;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "HPTRECEPTION")
public class HPTReceptionDo {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HTP_RESNO_GEN")
    @SequenceGenerator(sequenceName = "HTP_USERRESERVATION_NO_SEQ", name = "HTP_RESNO_GEN", allocationSize = 1)

    private Long no;

    private String pname;

    private String txlist;

    private LocalDateTime rdate;

    private String status;
}
