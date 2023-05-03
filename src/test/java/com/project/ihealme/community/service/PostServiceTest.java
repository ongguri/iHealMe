package com.project.ihealme.community.service;

import com.project.ihealme.community.dto.*;
import org.junit.jupiter.api.DisplayName;
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
        InsertPostRequestDTO insertPostRequestDTO = InsertPostRequestDTO.builder()
                .hptName("이지소아청소년과의원")
                .title("title 테스트2")
                .content("content 테스트2")
                .userEmail("user60@naver.com")
                .build();

        Long postNo = postService.write(insertPostRequestDTO);
        assertThat(postNo).isEqualTo(104);
    }

    @Test
    void getList1() {
        PageRequestDTO pageRequestDTO = new PageRequestDTO();
        PageResultDTO<PostResponseDTO, Object[]> result = postService.getList(pageRequestDTO);

        for (PostResponseDTO postResponseDTO : result.getDtoList()) {
            System.out.println(postResponseDTO);
        }
    }

    @Test
    @DisplayName("병원명 검색1")
    void getList2() {
        PageRequestDTO pageRequestDTO = new PageRequestDTO();
        pageRequestDTO.setType("h");
        pageRequestDTO.setKeyword("이지");
        PageResultDTO<PostResponseDTO, Object[]> result = postService.getList(pageRequestDTO);

        for (PostResponseDTO postResponseDTO : result.getDtoList()) {
            System.out.println(postResponseDTO);
        }
    }

    @Test
    @DisplayName("병원명 검색2")
    void getList3() {
        PageRequestDTO pageRequestDTO = new PageRequestDTO();
        pageRequestDTO.setPage(11);
        pageRequestDTO.setType("h");
        pageRequestDTO.setKeyword("새롬");
        PageResultDTO<PostResponseDTO, Object[]> result = postService.getList(pageRequestDTO);

        for (PostResponseDTO postResponseDTO : result.getDtoList()) {
            System.out.println(postResponseDTO);
        }

        System.out.println("totalPage: " + result.getTotalPage());
        System.out.println("isNext: " + result.isNext());
    }

    @Test
    void get() {
        PostResponseDTO postResponseDTO = postService.get(37L);
        System.out.println(postResponseDTO);
    }

    @Test
    void deleteWithReplies() {
        postService.deleteWithReplies(100L);
    }

    @Test
    void modify() {
        EditPostRequestDTO editPostRequestDTO = EditPostRequestDTO.builder()
                .postNo(99L)
                .title("title 수정")
                .content("content 수정")
                .build();

        postService.edit(editPostRequestDTO);
    }

}