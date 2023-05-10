package com.project.ihealme.community.dto;

import com.project.ihealme.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@Data
public class CommentDto {
    private Long commNo;
    private String content;
    private LocalDateTime regDate;
    private String email;
    private Long postNo;

    private String encodeEmail(User user) {
       return this.email = user.getEmail().substring(0, 3).concat("****");
    }

}