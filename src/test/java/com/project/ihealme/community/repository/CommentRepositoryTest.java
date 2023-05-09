package com.project.ihealme.community.repository;

import com.project.ihealme.community.domain.Comment;
import com.project.ihealme.community.domain.Post;
import com.project.ihealme.community.dto.CommentDto;
import com.project.ihealme.community.service.CommentServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CommentRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentServiceImpl service;

    @Test
    public void testList(){
        List<Comment> commentList = commentRepository.findByPost(
                Post.builder().postNo(97L).build());

        commentList.forEach(System.out::println);
    }

    @Test
    public void testGetList(){
        Long postNo = 100L;
        List<CommentDto> commentDtoList = service.getList(postNo);

        commentDtoList.forEach(System.out::println);
    }

}