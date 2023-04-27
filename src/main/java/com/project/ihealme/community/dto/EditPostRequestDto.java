package com.project.ihealme.community.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class EditPostRequestDto {

    private String title;
    private String content;
}
