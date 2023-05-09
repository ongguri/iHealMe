package com.project.ihealme.community.domain;

import com.project.ihealme.community.dto.PostResponseDTO;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "user")
@Entity
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POSTNO_GEN")
    @SequenceGenerator(sequenceName = "POST_POSTNO_SEQ", name = "POSTNO_GEN", allocationSize = 1)
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

    @Column(nullable = false)
    private int resNo;

    @Column(nullable = false)
    private String hptName;

//    @Column(nullable = false)
//    private String userEmail;

    @Column(nullable = false)
    private String title;

    @Lob
    private String content;

    @ColumnDefault("0")
    private int hit;

    @ColumnDefault("0")
    private int report;

    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER)
    @Transient
    private List<Comment> comments;

    public PostResponseDTO toPostResponseDTO() {
        PostResponseDTO postResponseDTO = PostResponseDTO.builder()
                .postNo(postNo)
                .hptName(hptName)
                .title(title)
                .content(content)
                .userEmail(user.getUserEmail())
                .regDate(getRegdate())
                .hit(hit)
                .report(report)
                .commentCount(comments.size())
                .build();

        return postResponseDTO;
    }

    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeContent(String content) {
        this.content = content;
    }

    public void addHitCount() {
        hit++;
    }

    public void addReportCount() {
        report++;
    }
}