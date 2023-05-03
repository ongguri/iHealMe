package com.project.ihealme.community.dto;

import com.project.ihealme.community.domain.User;
import com.project.ihealme.community.domain.Post;
import lombok.*;

@Builder
@AllArgsConstructor
@Getter @Setter
@ToString
public class InsertPostRequestDTO {

    private int resNo;
    private String hptName;
    private String userEmail;
    private String title;
    private String content;

    public Post toEntity(User user) {
        Post post = Post.builder()
                .resNo(resNo)
                .user(user)
                .hptName(hptName)
                .title(title)
                .content(content)
                .build();

        return post;
    }

}
