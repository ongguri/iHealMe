package com.project.ihealme.community.dto;

import com.project.ihealme.community.domain.User;
import com.project.ihealme.community.domain.Post;
import com.project.ihealme.userReservation.domain.UserReservation;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
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

/*    public PostResponseDTO(Post post, User user, UserReservation userReservation, int commentCount) {
        this.postNo = post.getPostNo();
        this.hptName = userReservation.getHptName();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.regDate = post.getRegdate();
        this.hit = post.getHit();
        this.report = post.getReport();
        this.userEmail = encodeUserEmail(user);
        this.commentCount = commentCount;
    }*/

    public PostResponseDTO(Post post, User user, UserReservation userReservation) {
        this.postNo = post.getPostNo();
        this.hptName = userReservation.getHptName();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.regDate = post.getRegdate();
        this.hit = post.getHit();
        this.report = post.getReport();
        this.userEmail = encodeUserEmail(user);
        this.commentCount = post.getComments().size();
    }

    public PostResponseDTO(Post post) {
        this.postNo = post.getPostNo();
        this.hptName = post.getUserReservation().getHptName();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.regDate = post.getRegdate();
        this.hit = post.getHit();
        this.report = post.getReport();
        this.userEmail = encodeUserEmail(post.getUser());
        this.commentCount = post.getComments().size();
    }

    @QueryProjection
    public PostResponseDTO(Long postNo, String hptName, String title, String content, String userEmail, LocalDateTime regDate, int hit, int report, int commentCount) {
        this.postNo = postNo;
        this.hptName = hptName;
        this.title = title;
        this.content = content;
        this.userEmail = userEmail;
        this.regDate = regDate;
        this.hit = hit;
        this.report = report;
        this.commentCount = commentCount;
    }

    private String encodeUserEmail(User user) {
        return user.getUserEmail().substring(0, 3).concat("****");
    }
}
