package com.project.ihealme.community.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Entity
@Table(name = "COMENTS")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMMNO_GEN")
    @SequenceGenerator(sequenceName = "COMMENT_COMMNO_SEQ", name = "COMMNO_GEN", allocationSize = 1)
    private Long commNo;

    @Column(nullable = false, length = 600)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERNO", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POSTNO", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Post post;

    @CreatedDate
    private LocalDateTime regDate;

    public Comment(String content, User user, Post post, LocalDateTime regDate) {
        this.content = content;
        this.user = user;
        this.post = post;
        this.regDate = regDate;
    }


    public boolean isOwnComment(User user){
        return this.user.equals(user);
    }
}
