package com.project.ihealme.community.controller;

import com.project.ihealme.community.domain.User;
import com.project.ihealme.community.dto.CommentCreateRequest;
import com.project.ihealme.community.dto.CommentDto;
import com.project.ihealme.community.dto.CommentUpdateRequest;
import com.project.ihealme.community.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/post/{postNo}/comment")       //댓글 목록
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDto> findAllComments(@Valid @PathVariable Long postNo) {
        return commentService.findAllComments(postNo);
    }

    @PostMapping("/post/{postNo}/comment")      //댓글 작성
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@PathVariable Long postNo, @AuthenticationPrincipal User user, @RequestBody CommentCreateRequest req) {

        commentService.createComment(req, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/post/{postNo}/comment/{commNo}")       //댓글 수정
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> update(@PathVariable Long commNo, @AuthenticationPrincipal User user, CommentUpdateRequest req, @PathVariable String postNo) {

        commentService.updateComment(commNo, user, req);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/post/{postNo}/comment/{commNo}")      //댓글 삭제
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> delete(@PathVariable Long commNo, @AuthenticationPrincipal User user, @PathVariable String postNo){

        commentService.deleteComment(commNo, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
