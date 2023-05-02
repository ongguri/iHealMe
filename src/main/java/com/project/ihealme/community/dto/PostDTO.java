package com.project.ihealme.community.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString
public class PostDTO {

    private Long postNo;
    private int resNo;
    private String hptName;
    private String title;
    private String content;
    private String userEmail;
    private LocalDateTime regDate;
    private int hit;
    private int report;
    private int commentCount;

}
