package com.project.ihealme.community.service;

import com.project.ihealme.community.domain.Comment;
import com.project.ihealme.community.domain.Post;
import com.project.ihealme.community.dto.CommentDto;
import com.project.ihealme.user.entity.User;

import java.util.List;

public interface CommentService {
    Long save(CommentDto commentDto); //댓글 등록
    List<CommentDto> getList(Long postNo); //특정 게시글 댓글 불러오기
    void update(CommentDto commentDto); //댓글 수정
    void delete(Long commNo); //댓글 삭제

    default Comment toEntitiy(CommentDto commentDto){
        Post post = Post.builder()
                .postNo(commentDto.getPostNo())
                .build();

        User user = User.builder()
                .email(commentDto.getEmail())
                .build();

        return Comment.builder()
                .commNo(commentDto.getCommNo())
                .content(commentDto.getContent())
                .user(user)
                .post(post)
                .build();
    }

    default CommentDto toDto(Comment comment){

        return CommentDto.builder()
                .commNo(comment.getCommNo())
                .content(comment.getContent())
                .email(comment.getUser().getEmail())
                .regDate(comment.getRegDate())
                .build();
    }
}