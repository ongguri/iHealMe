package com.project.ihealme.community.dto;

import com.project.ihealme.community.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class CommentPageDto {
    private int commentCnt;
    private List<CommentDto> list;

    public static CommentPageDto createCommentPageDto(int commentCnt, List<Comment> list) {
        List<CommentDto> commentDtoList = new ArrayList<>();
        for (Comment comment : list) {
            commentDtoList.add(CommentDto.builder()
                                .commNo(comment.getCommNo())
                                .content(comment.getContent())
                                .regDate(comment.getRegdate())
                                .email(comment.getUser().getEmail())
                                .postNo(comment.getPost().getPostNo())
                                .build());
        }

        return new CommentPageDto(commentCnt, commentDtoList);
    }
}
