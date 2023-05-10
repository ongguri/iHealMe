package com.project.ihealme.community.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.project.ihealme.user.entity.User;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentDto {
    private Long commNo;
    private String content;
    private LocalDateTime regDate;
    private String email;
    private Long postNo;

    private String encodeEmail(User user) {
        return user.getEmail().substring(0, 3).concat("****");
    }
}