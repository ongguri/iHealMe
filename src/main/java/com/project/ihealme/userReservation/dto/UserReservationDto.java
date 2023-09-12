package com.project.ihealme.userReservation.dto;

import com.project.ihealme.userReservation.domain.UserReservation;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter @Getter
public class UserReservationDto {

    private Long resNo;
    private String patientName;
    private String txList;
    private String currentStatus;
    private LocalDateTime regDate;
    private LocalDateTime updateRegDate;

    protected UserReservationDto() {
    }

    private UserReservationDto(Long resNo, String patientName, String txList, String currentStatus, LocalDateTime regDate, LocalDateTime updateRegDate) {
        this.resNo = resNo;
        this.patientName = patientName;
        this.txList = txList;
        this.currentStatus = currentStatus;
        this.regDate = regDate;
        this.updateRegDate = updateRegDate;
    }

    public static UserReservationDto of(String patientName, String txList, String currentStatus) {
        return new UserReservationDto(null, patientName, txList, currentStatus, null, null);
    }

    public static UserReservationDto of(Long resNo, String patientName, String txList, String currentStatus, LocalDateTime regDate, LocalDateTime updateRegDate) {
        return new UserReservationDto(resNo, patientName, txList, currentStatus, regDate, updateRegDate);
    }

    public static UserReservationDto from(UserReservation entity) {
        return new UserReservationDto(
                entity.getResNo(),
                entity.getPatientName(),
                entity.getTxList(),
                entity.getCurrentStatus(),
                entity.getRegDate(),
                entity.getUpdateRegDate()
        );
    }
}
