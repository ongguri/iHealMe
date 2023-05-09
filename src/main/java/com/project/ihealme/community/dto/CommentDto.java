package com.project.ihealme.community.dto;

import com.project.ihealme.community.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentDto {
    private Long commNo;
    private String content;
    private LocalDateTime regDate;
    private String userEmail;
    private Long postNo;

    private String encodeUserEmail(User user) {
        return user.getUserEmail().substring(0, 3).concat("****");
    }
}
