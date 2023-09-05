package com.project.ihealme.HptReception.dto;

import com.project.ihealme.HptReception.domain.HptReception;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
public class HptRecPageResponseDTO {

    private List<HptReception> hptRecList; //HptReception 리스트
    private int totalPage; //총 페이지 번호
    private int currentPage; //현재 페이지 번호
    private int size; //목록 사이즈
    private int start, end; //시작 페이지 번호, 끝 페이지 번호
    private boolean prev, next; //이전, 다음
    private List<Integer> pageList; //페이지 번호 목록

    public HptRecPageResponseDTO(Page<HptReception> result) {
        hptRecList = result.stream().collect(Collectors.toList());
        totalPage = result.getTotalPages();
        makeInformation(result.getPageable());
    }

    private void makeInformation(Pageable pageable) {
        currentPage = pageable.getPageNumber() + 1;
        size = pageable.getPageSize();

        int tempEnd = (int) (Math.ceil(currentPage / 10.0)) * 10;
        start = tempEnd - 9;
        prev = start > 1;

        end = totalPage > tempEnd ? tempEnd : totalPage;
        next = totalPage > tempEnd;

        pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
    }
}
