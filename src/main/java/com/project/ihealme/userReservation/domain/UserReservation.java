package com.project.ihealme.userReservation.domain;

import com.project.ihealme.community.domain.BaseEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Table(name="USERRESERVATION")
public class UserReservation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RESNO_GEN")
    @SequenceGenerator(sequenceName = "USERRESERVATION_NO_SEQ", name = "RESNO_GEN", allocationSize = 1)
    @Column(name = "RESNO") private Long resNo;

    @Column(name = "PATIENTNAME") private String patientName;
    @Column(name = "TXLIST") private String txList;
    @Column(name = "CURRENTSTATUS") @ColumnDefault("'접수대기'") private String currentStatus;

}
