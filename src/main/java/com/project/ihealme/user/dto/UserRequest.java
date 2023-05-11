package com.project.ihealme.user.dto;

import com.project.ihealme.user.entity.UserRole;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {

    private String name;

    private String email;

    private String phoneNum;

    private String birthDate;

    private String gender;

    private String password;

    private String confirmPassword;

    private String question;

    private String answer;

    private UserRole userRole;

}