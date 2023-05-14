package com.project.ihealme.community.domain;

public enum SearchType {

    HOSPITAL_NAME("h", "병원명"),
    TITLE("t", "제목"),
    WRITER("w", "작성자");

    private final String type;
    private final String description;

    SearchType(String type, String description) {
        this.type = type;
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
}
