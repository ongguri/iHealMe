package com.project.ihealme.user.dto;

import com.project.ihealme.user.entity.UserRole;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    private Long id;

    private String email;

    private String password;

    private UserRole userRole;

    @Builder
    private UserDTO(Long id, String password, UserRole userRole, String email) {
        this.id = id;
        this.password = password;
        this.userRole = userRole;
        this.email = email;
    }

}