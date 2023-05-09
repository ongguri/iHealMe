package com.project.ihealme.community.controller;

import com.project.ihealme.community.dto.CommentDto;
import com.project.ihealme.community.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/community")
@Log4j2
@RestController
public class CommentController {

    private final CommentService commentService;

    @GetMapping(value = "/post/{postNo}", produces = MediaType.APPLICATION_JSON_VALUE)      //댓글 목록
    public ResponseEntity<List<CommentDto>> getListByPost(@PathVariable("postNo") Long postNo){
        log.info("postNo: " + postNo);

        return new ResponseEntity<>(commentService.getList(postNo), HttpStatus.OK);
    }

    @PostMapping("/post/{postNo}")
    public ResponseEntity<Long> save(@RequestBody CommentDto commentDto){
        log.info(commentDto);
        Long commNo = commentService.save(commentDto);

        return new ResponseEntity<>(commNo, HttpStatus.OK);
    }

    @DeleteMapping("/{commNo}")
    public ResponseEntity<String> delete(@PathVariable("commNo")Long commNo){
        log.info("COMMNO: " + commNo);
        commentService.delete(commNo);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @PutMapping("/{commNo}")
    public ResponseEntity<String> update(@RequestBody CommentDto commentDto){
        log.info(commentDto);
        commentService.update(commentDto);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    /*@GetMapping("/post/{postNo}/comment")       //댓글 목록
    @ResponseStatus(HttpStatus.OK)
    public List<CommentResponseDto> findAllComments(@Valid @PathVariable Long postNo) {
        return commentServiceImpl.findAllComments(postNo);
    }

    @PostMapping("/post/{postNo}/comment")      //댓글 작성
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> saveComment(@PathVariable Long postNo, @AuthenticationPrincipal User user, @RequestBody CommentRequestDto dto) {
        commentServiceImpl.saveComment(dto, postNo, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/post/{postNo}/comment/{commNo}")       //댓글 수정
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> updateComment(@PathVariable Long commNo, @PathVariable String postNo, @AuthenticationPrincipal User user, @RequestBody CommentRequestDto dto) {

        commentServiceImpl.updateComment(commNo, user, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/post/{postNo}/comment/{commNo}")      //댓글 삭제
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> deleteComment(@PathVariable Long commNo, @PathVariable String postNo, @AuthenticationPrincipal User user){

        commentServiceImpl.deleteComment(commNo, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }*/

}
