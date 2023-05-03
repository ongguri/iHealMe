package com.project.ihealme.community.domain;

import com.project.ihealme.community.dto.EditPostRequestDto;
import com.project.ihealme.community.dto.InsertPostRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "POST")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "POSTNO_GEN")
    @SequenceGenerator(sequenceName = "POST_POSTNO_SEQ", name = "POSTNO_GEN", allocationSize = 1)
    private Long postno;

/*@OneToOne(fetch = FetchType.LAZY)
@JoinColumns({
@JoinColumn(name = "resno"),
@JoinColumn(name = "hptname", referencedColumnName = "hpt_name"),
@JoinColumn(name = "useremail", referencedColumnName = "user_email")
})
private UserReservation userReservation;*/

    private Long resno;
    private String hptname;
    private String useremail;

/*@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "useremail")
private User user;*/

    @Column(nullable = false, length = 100)
    private String title;

    @Lob
    private String content;

    @CreatedDate
    private LocalDateTime regdate;

    @ColumnDefault("0")
    private Long hit;

    @ColumnDefault("0")
    private Long report;

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
}