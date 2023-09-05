package com.project.ihealme.community.dto;

import com.project.ihealme.community.domain.Post;
import com.project.ihealme.user.entity.User;
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
    private Long userId;
    private String userEmail;
    private LocalDateTime regDate;
    private int hit;
    private int report;
    private int commentCount;

    public PostResponseDTO(Post post) {
        this(post, post.getHit());
    }

    public PostResponseDTO(Post post, int hit) {
        this.postNo = post.getPostNo();
        this.hptName = post.getUserReservation().getPatientName();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.regDate = post.getRegdate();
        this.hit = hit;
        this.report = post.getReport();
        this.userId = post.getUser().getUserId();
        this.userEmail = encodeUserEmail(post.getUser());
        this.commentCount = post.getComments().size();
    }

    private String encodeUserEmail(User user) {
        return user.getEmail().substring(0, 3).concat("****");
    }
}
