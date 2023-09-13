package com.project.ihealme.HptReception.dto.request;

import com.project.ihealme.HptReception.dto.HptReceptionDto;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class HptReceptionRequest {

    private String currentStatus;

    protected HptReceptionRequest() {
    }

    private HptReceptionRequest(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public static HptReceptionRequest of(String currentStatus) {
        return new HptReceptionRequest(
            currentStatus
        );
    }

    public HptReceptionDto toDto() {
        return HptReceptionDto.of(
            currentStatus
        );
    }
}
