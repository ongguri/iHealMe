package com.project.ihealme.community.dto;

import com.project.ihealme.community.domain.Post;
import com.project.ihealme.community.domain.User;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString
public class PostResponseDTO {

    private Long postNo;
    private String hptName;
    private String title;
    private String content;
    private String userEmail;
    private LocalDateTime regDate;
    private int hit;
    private int report;
    private int commentCount;

    public PostResponseDTO(Post post, User user, int commentCount) {
        this.postNo = post.getPostNo();
        this.hptName = post.getHptName();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.regDate = post.getRegdate();
        this.hit = post.getHit();
        this.report = post.getReport();
        this.userEmail = encodeUserEmail(user);
        this.commentCount = commentCount;
    }

    public PostResponseDTO(Post post, User user) {
        this(post, user, 0);
    }

    public Post toEntity(PostResponseDTO postResponseDTO, User user) {
        Post post = Post.builder()
                .postNo(postResponseDTO.getPostNo())
                .user(user)
                .hptName(postResponseDTO.getHptName())
                .title(postResponseDTO.getTitle())
                .content(postResponseDTO.getContent())
                .hit(postResponseDTO.getHit())
                .report(postResponseDTO.getReport())
                .build();

        return post;
    }

    private String encodeUserEmail(User user) {
        return user.getUserEmail().substring(0, 3).concat("****");
    }
}