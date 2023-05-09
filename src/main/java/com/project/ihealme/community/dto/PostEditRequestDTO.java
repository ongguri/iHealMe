package com.project.ihealme.community.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@Getter @Setter
@ToString
public class PostEditRequestDTO {

    private Long postNo;
    private String title;
    private String content;

}
