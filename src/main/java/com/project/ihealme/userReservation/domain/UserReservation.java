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
    @Column(name = "RESNO")
    private Long resNo;

    @Column(name = "EMAIL", updatable=false)
    private String email;

    @Column(name = "NAME", updatable=false)
    private String name;

    @Column(name = "LIST", updatable=false)
    private String list;

    @Column(name = "CURRENTSTATUS")
    @ColumnDefault("'접수대기'")
    private String currentStatus;

//    public UserReservation toEntity(UserReservation userRes) {
//        UserReservation userReservation = userRes.builder()
//                .resNo(resNo)
//                .currentStatus("접수취소")
//                .build();
//        return userReservation;
//    }
}
