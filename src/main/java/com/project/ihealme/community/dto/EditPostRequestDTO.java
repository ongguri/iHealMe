package com.project.ihealme.community.dto;

import com.project.ihealme.community.domain.Post;
import lombok.*;

@Builder
@AllArgsConstructor
@Getter @Setter
@ToString
public class EditPostRequestDTO {

    private Long postNo;
    private String title;
    private String content;

    /*public Post toEntity() {
        Post post = Post.builder()
                .postNo(postNo)
                .title(title)
                .content(content)
                .build();

        return post;
    }*/
}
