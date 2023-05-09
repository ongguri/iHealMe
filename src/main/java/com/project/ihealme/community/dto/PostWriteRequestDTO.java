package com.project.ihealme.community.dto;

import com.project.ihealme.community.domain.User;
import com.project.ihealme.community.domain.Post;
import com.project.ihealme.userReservation.domain.UserReservation;
import lombok.*;

@Builder
@AllArgsConstructor
@Getter @Setter
@ToString
public class PostWriteRequestDTO {

    private Long userId;
    private Long resNo;
    private String title;
    private String content;

}
