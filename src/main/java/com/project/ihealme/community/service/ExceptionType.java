package com.project.ihealme.community.service;

public enum ExceptionType {
    EXCEPTION("예외 발생"),
    POST_NOT_FOUND("게시글이 존재하지 않습니다."),
    USER_NOT_LOGIN("로그인이 필요합니다."),
    USER_RESERVATION_NOT_FOUND("존재하지 않는 접수내역 입니다."),
    USER_NOT_MATCH_POST_WRITER("해당 게시글의 작성자가 아닙니다."),
    POST_WRITE_NOT_ALLOWED("게시글을 작성할 수 없습니다."),
    POST_EDIT_NOT_ALLOWED("게시글을 수정할 수 없습니다."),
    POST_REPORT_NOT_ALLOWED("게시글을 신고할 수 없습니다."),
    INVALID_SEARCH_TYPE("유효하지 않은 검색 유형입니다.");

    private final String message;

    ExceptionType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
