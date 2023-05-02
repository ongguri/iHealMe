package com.project.ihealme.community.domain;

import com.project.ihealme.community.dto.EditPostRequestDto;
import com.project.ihealme.community.dto.InsertPostRequestDto;
import com.project.ihealme.community.dto.PostDTO;
import com.project.ihealme.userReservation.domain.UserReservation;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "user")
@Entity
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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