package com.project.ihealme.HptReception.domain;

import com.project.ihealme.community.domain.BaseEntity;
import com.project.ihealme.userReservation.domain.UserReservation;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name="HPTRECEPTION")
public class HptReception extends BaseEntity {

        @Id @Column(name = "RECNO", length = 20)
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RECNO_GEN")
        @SequenceGenerator(sequenceName = "HPTRECEPTION_NO_SEQ", name = "RECNO_GEN", allocationSize = 1)
        private Long recNo;

        @ManyToOne(optional = false) private UserReservation userReservation;

        @Column(name = "RTCOUNT", length = 10) @ColumnDefault("0") private int rtCount;

}

