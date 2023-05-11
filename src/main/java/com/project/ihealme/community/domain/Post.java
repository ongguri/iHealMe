package com.project.ihealme.community.domain;

import com.project.ihealme.community.dto.PostWriteRequestDTO;
import com.project.ihealme.user.entity.User;
import com.project.ihealme.userReservation.domain.UserReservation;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"user", "userReservation"})
@Entity
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POSTNO_GEN")
    @SequenceGenerator(sequenceName = "POST_POSTNO_SEQ", name = "POSTNO_GEN", allocationSize = 1)
    @Column(name = "POSTNO")
    private Long postNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERID")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RESNO", unique = true, nullable = false)
    private UserReservation userReservation;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob
    private String content;

    @ColumnDefault("0")
    private int hit;

    @ColumnDefault("0")
    private int report;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    @BatchSize(size = 10)
    @OrderBy("commNo desc")
    private List<Comment> comments;

    public static Post create(PostWriteRequestDTO postWriteRequestDTO, User user, UserReservation userReservation) {
        Post post = Post.builder()
                .user(user)
                .userReservation(userReservation)
                .title(postWriteRequestDTO.getTitle())
                .content(postWriteRequestDTO.getContent())
                .build();

        return post;
    }

    public void edit(String title, String content) {
        this.title = title;
        this.content = content;
    }
}