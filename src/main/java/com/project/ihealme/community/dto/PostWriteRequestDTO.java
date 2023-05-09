package com.project.ihealme.community.dto;

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
