package com.project.ihealme.community.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CommentCreateRequest {

    @NotNull
    @Positive
    private Long postNo;

    @NotBlank
    private String content;
}
