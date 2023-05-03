package com.project.ihealme.community.dto;

import com.project.ihealme.community.domain.User;
import com.project.ihealme.community.domain.Post;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString
public class PostResponseDTO {

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

    public Post toEntity(PostResponseDTO postResponseDTO, User user) {
        Post post = Post.builder()
                .postNo(postResponseDTO.getPostNo())
                .resNo(postResponseDTO.getResNo())
                .user(user)
                .hptName(postResponseDTO.getHptName())
                .title(postResponseDTO.getTitle())
                .content(postResponseDTO.getContent())
                .hit(postResponseDTO.getHit())
                .report(postResponseDTO.getReport())
                .build();

        return post;
    }
}
