package com.project.ihealme.community.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERID_GEN")
    @SequenceGenerator(sequenceName = "USER_USERID_SEQ", name = "USERID_GEN", allocationSize = 1)
    private Long userId;

    @Column(unique = true)
    private String userEmail;


}
