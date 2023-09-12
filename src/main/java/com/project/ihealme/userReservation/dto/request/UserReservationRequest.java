package com.project.ihealme.userReservation.dto.request;

import com.project.ihealme.userReservation.dto.UserReservationDto;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserReservationRequest {

    private String patientName;
    private String txList;
    private String currentStatus;

    protected UserReservationRequest() {
    }

    private UserReservationRequest(String patientName, String txList, String currentStatus) {
        this.patientName = patientName;
        this.txList = txList;
        this.currentStatus = currentStatus;
    }

    public static UserReservationRequest of(String patientName, String txList, String currentStatus) {
        return new UserReservationRequest(patientName, txList, currentStatus);
    }

    public UserReservationDto toDto() {
        return UserReservationDto.of(
                patientName,
                txList,
                currentStatus
        );
    }

}
