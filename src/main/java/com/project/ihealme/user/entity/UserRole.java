package com.project.ihealme.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserRole {
    USER("ROLE_USER"),
    HOSPITAL("ROLE_HOSPITAL");

    private String value;
}