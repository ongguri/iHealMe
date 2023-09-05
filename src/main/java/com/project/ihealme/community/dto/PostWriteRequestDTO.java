package com.project.ihealme.community.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString
public class PostWriteRequestDTO {

    @NotNull
    private Long userId;

    @NotNull
    private Long resNo;

    @NotBlank
    private String hptName;

    @NotBlank
    private String title;

    private String content;

    public PostWriteRequestDTO(Long userId, Long resNo, String hptName) {
        this.userId = userId;
        this.resNo = resNo;
        this.hptName = hptName;
    }
}
