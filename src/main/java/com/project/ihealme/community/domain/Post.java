package com.project.ihealme.community.domain;

import com.project.ihealme.community.dto.PostWriteRequestDTO;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "user")
@Entity
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POSTNO_GEN")
    @SequenceGenerator(sequenceName = "POST_POSTNO_SEQ", name = "POSTNO_GEN", allocationSize = 1)
    @Column(name = "POSTNO")
    private Long postNo;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumns({
//            @JoinColumn(name = "resNo", referencedColumnName = "resNo"),
//            @JoinColumn(name = "hptName", referencedColumnName = "hptName")
//    })
//    private UserReservation userReservation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "resNo")
//    private Reservation reservation;

    @Column(name = "RESNO", nullable = false)
    private int resNo;

    @Column(name = "HPTNAME", nullable = false)
    private String hptName;

//    @Column(nullable = false)
//    private String userEmail;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(length = 4000)
    private String content;

    @ColumnDefault("0")
    private int hit;

    @ColumnDefault("0")
    private int report;

    @OneToMany(mappedBy = "post", orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    public static Post create(PostWriteRequestDTO postWriteRequestDTO, User user) {
        Post post = Post.builder()
                .user(user)
                .resNo(postWriteRequestDTO.getResNo())
                .hptName(postWriteRequestDTO.getHptName())
                .title(postWriteRequestDTO.getTitle())
                .content(postWriteRequestDTO.getContent())
                .build();

        return post;
    }

    public void edit(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void addHitCount() {
        hit++;
    }

    public void addReportCount() {
        report++;
    }
}