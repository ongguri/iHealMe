package com.project.ihealme.community.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString
public class PostWriteRequestDTO {

    private Long userId;
    private Long resNo;
    private String hptName;
    private String title;
    private String content;

    public PostWriteRequestDTO(Long userId, Long resNo, String hptName) {
        this.userId = userId;
        this.resNo = resNo;
        this.hptName = hptName;
    }
}
