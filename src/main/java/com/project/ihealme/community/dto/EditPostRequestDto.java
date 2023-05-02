package com.project.ihealme.community.dto;

import com.project.ihealme.community.domain.Post;
import com.project.ihealme.community.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class EditPostRequestDto {

    private String title;
    private String content;

}
