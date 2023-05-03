package com.project.ihealme.community.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.ihealme.community.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentDto {
    private Long commNo;
    private String content;
    private Long userNo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd", timezone = "Asia/Seoul")
    private LocalDateTime regDate;


    public static CommentDto toDto(Comment comment){
        return new CommentDto(
                comment.getCommNo(),
                comment.getContent(),
                comment.getUser().getUserNo(),
                comment.getRegDate()
        );
    }
}
