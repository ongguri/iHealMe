package com.project.ihealme.community.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Criteria {
    private int pageNum;
    private int amount;     //한 페이지에 보여줄 개수

    public Criteria(){
        this(1,3);
    }

    public Criteria(int pageNum, int amount){
        this.pageNum = pageNum;
        this.amount = amount;
    }
}
