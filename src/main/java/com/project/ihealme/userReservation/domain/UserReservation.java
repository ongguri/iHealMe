package com.project.ihealme.userReservation.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class UserReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resNo;
    private String userEmail;
    private String hptName;

    private String txList;
    private LocalDateTime rDate;
    private String status;

    public Long getResNo() {
        return resNo;
    }

    public void setResNo(Long resNo) {
        this.resNo = resNo;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getHptName() {
        return hptName;
    }

    public void setHptName(String hptName) {
        this.hptName = hptName;
    }

    public String getTxList() {
        return txList;
    }

    public void setTxList(String txList) {
        this.txList = txList;
    }

    public LocalDateTime getrDate() {
        return rDate;
    }

    public void setrDate(LocalDateTime rDate) {
        this.rDate = rDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
