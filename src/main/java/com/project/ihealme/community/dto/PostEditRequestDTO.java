package com.project.ihealme.community.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@AllArgsConstructor
@Getter @Setter
@ToString
public class PostEditRequestDTO {

    @NotNull
    private Long postNo;

    @NotBlank
    private String title;

    private String content;

}
