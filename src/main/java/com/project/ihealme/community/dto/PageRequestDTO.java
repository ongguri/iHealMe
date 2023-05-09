package com.project.ihealme.community.dto;

import lombok.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Builder
@AllArgsConstructor
@Getter @Setter
@ToString
public class PageRequestDTO {

    private int page; //현재 페이지
    private int size; //현재 페이지 게시글 개수
    private String type;
    private String keyword;

    /**
     * 1페이지, 10개 게시글로 초기화
     */
    public PageRequestDTO() {
        this.page = 1;
        this.size = 10;
    }

    public Pageable getPageable(Sort sort) {
        return PageRequest.of(page - 1, size, sort);
    }
}