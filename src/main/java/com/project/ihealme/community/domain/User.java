package com.project.ihealme.community.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERID_GEN")
    @SequenceGenerator(sequenceName = "USER_USERID_SEQ", name = "USERID_GEN", allocationSize = 1)
    private Long userNo;
}
