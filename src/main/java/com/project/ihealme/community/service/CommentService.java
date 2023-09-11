package com.project.ihealme.community.service;

import com.project.ihealme.community.domain.Comment;
import com.project.ihealme.community.domain.Criteria;
import com.project.ihealme.community.dto.CommentDto;
import com.project.ihealme.community.dto.CommentPageDto;
import com.project.ihealme.user.entity.User;

import java.util.List;

public interface CommentService {
    Long save(User user, CommentDto commentDto); //댓글 등록
    List<CommentDto> getList(Long postNo); //특정 게시글 댓글 불러오기
    void update(User user, CommentDto commentDto); //댓글 수정
    void delete(User user, Long commNo); //댓글 삭제
    CommentPageDto getListPage(Criteria criteria, Long postNo);

    default CommentDto toDto(Comment comment){

        return CommentDto.builder()
                .commNo(comment.getCommNo())
                .postNo(comment.getPost().getPostNo())
                .content(comment.getContent())
                .email(comment.getUser().getEmail().substring(0, 3).concat("****"))
                .regDate(comment.getRegDate())
                .build();
    }
}