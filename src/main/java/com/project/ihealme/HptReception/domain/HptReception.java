package com.project.ihealme.HptReception.domain;

import com.project.ihealme.userReservation.domain.UserReservation;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name="HPTRECEPTION")
public class HptReception {

        @Id @Column(name = "RESNO", length = 20)
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RECNO_GEN")
        @SequenceGenerator(sequenceName = "HPTRECEPTION_NO_SEQ", name = "RECNO_GEN", allocationSize = 1)
        private int resNo;

        @ManyToOne(optional = false) private UserReservation userReservation;

        @Column(name = "RTCOUNT", length = 10) @ColumnDefault("0") private int rtCount;

}

