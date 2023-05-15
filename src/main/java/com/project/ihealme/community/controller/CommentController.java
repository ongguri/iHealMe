package com.project.ihealme.community.controller;

import com.project.ihealme.community.domain.Criteria;
import com.project.ihealme.community.dto.CommentDto;
import com.project.ihealme.community.dto.CommentPageDto;
import com.project.ihealme.community.service.CommentService;
import com.project.ihealme.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/community")
@Log4j2
@RestController
public class CommentController {

    private final CommentService commentService;

    @GetMapping(value = "/{postNo}", produces = MediaType.APPLICATION_JSON_VALUE)      //댓글 목록
    public ResponseEntity<List<CommentDto>> getListByPost(@PathVariable("postNo") Long postNo){
        log.info("postNo: " + postNo);

        return new ResponseEntity<>(commentService.getList(postNo), HttpStatus.OK);
    }

    @GetMapping(value = "/{postNo}/{page}", produces = MediaType.APPLICATION_JSON_VALUE)      //댓글 목록 페이징
    public ResponseEntity<CommentPageDto> getListPageByPost(@PathVariable("postNo") Long postNo, @PathVariable("page") int page){
        log.info("postNo: " + postNo + "page: " + page);
        Criteria cri = new Criteria(page, 3);
        return new ResponseEntity<>(commentService.getListPage(cri, postNo) , HttpStatus.OK);
    }

    @PostMapping("{postNo}")
    public ResponseEntity<Long> save(@AuthenticationPrincipal User user, @RequestBody CommentDto commentDto){
        log.info(commentDto);
        Long commNo = commentService.save(user, commentDto);

        return new ResponseEntity<>(commNo, HttpStatus.OK);
    }

    @DeleteMapping("/comment/{commNo}")
    public ResponseEntity<String> delete(@AuthenticationPrincipal User user, @PathVariable("commNo")Long commNo){
        log.info("COMMNO: " + commNo);
        commentService.delete(user, commNo);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @PutMapping("/comment/{commNo}")
    public ResponseEntity<String> update(@AuthenticationPrincipal User user, @RequestBody CommentDto commentDto){
        log.info(commentDto);
        commentService.update(user, commentDto);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
