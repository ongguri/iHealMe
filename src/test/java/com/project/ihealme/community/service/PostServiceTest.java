package com.project.ihealme.community.service;

import com.project.ihealme.community.dto.PageRequestDTO;
import com.project.ihealme.community.dto.PageResultDTO;
import com.project.ihealme.community.dto.PostDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class PostServiceTest {

    @Autowired
    private PostService postService;

    @Test
    void write() {
        PostDTO postDTO = PostDTO.builder()
                .hptName("이지소아청소년과의원")
                .title("title 테스트2")
                .content("content 테스트2")
                .userEmail("user60@naver.com")
                .build();

        Long postNo = postService.write(postDTO);
        assertThat(postNo).isEqualTo(104);
    }

    @Test
    void getList1() {
        PageRequestDTO pageRequestDTO = new PageRequestDTO();
        PageResultDTO<PostDTO, Object[]> result = postService.getList(pageRequestDTO);

        for (PostDTO postDTO : result.getDtoList()) {
            System.out.println(postDTO);
        }
    }

    @Test
    void getList2() {
        PageRequestDTO pageRequestDTO = new PageRequestDTO();
        PageResultDTO<PostDTO, Object[]> result = postService.getList(pageRequestDTO);

        for (PostDTO postDTO : result.getDtoList()) {
            System.out.println(postDTO);
        }
    }

    @Test
    void get() {
        PostDTO postDTO = postService.get(37L);
        System.out.println(postDTO);
    }

    @Test
    void deleteWithReplies() {
        postService.deleteWithReplies(100L);
    }

    @Test
    void modify() {
        PostDTO postDTO = PostDTO.builder()
                .postNo(99L)
                .title("title 수정")
                .content("content 수정")
                .build();

        postService.edit(postDTO);
    }

}