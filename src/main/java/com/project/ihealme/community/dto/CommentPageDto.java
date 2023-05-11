package com.project.ihealme.community.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CommentPageDto {
    private int commentCnt;
    private List<CommentDto> list;
}
