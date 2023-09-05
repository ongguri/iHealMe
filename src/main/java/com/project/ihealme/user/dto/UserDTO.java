package com.project.ihealme.user.dto;

import com.project.ihealme.user.entity.UserRole;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    private Long userId;

    private String email;

    private String password;

    private UserRole userRole;

    @Builder
    private UserDTO(Long userId, String email, String password, UserRole userRole) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.userRole = userRole;
    }

    @Builder
    private UserDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

}