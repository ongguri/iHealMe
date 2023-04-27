package com.project.ihealme.community.domain;

import com.project.ihealme.community.dto.EditPostRequestDto;
import com.project.ihealme.community.dto.InsertPostRequestDto;
import com.project.ihealme.userReservation.domain.UserReservation;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Post extends RegdateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "postno")
    private Long postNo;

    /*@OneToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "resno"),
            @JoinColumn(name = "hptname", referencedColumnName = "hpt_name"),
            @JoinColumn(name = "useremail", referencedColumnName = "user_email")
    })
    private UserReservation userReservation;*/

    @Column(name = "resno")
    private Long resNo;

    @Column(name = "hptname")
    private String hptName;

    @Column(name = "useremail")
    private String userEmail;

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "useremail")
    private User user;*/

    @Column(nullable = false, length = 100)
    private String title;

    @Lob
    private String content;

    @ColumnDefault("0")
    private int hit;

    @ColumnDefault("0")
    private int report;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    public Post(InsertPostRequestDto insertPostRequestDto) {
        title = insertPostRequestDto.getTitle();
        content = insertPostRequestDto.getContent();
    }

    public void editPost(EditPostRequestDto editPostRequestDto) {
        title = editPostRequestDto.getTitle();
        content = editPostRequestDto.getContent();
    }

    public void addHitCount() {
        hit++;
    }

    public void addReportCount() {
        report++;
    }
}