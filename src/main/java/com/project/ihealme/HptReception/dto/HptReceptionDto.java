package com.project.ihealme.HptReception.dto;

import com.project.ihealme.HptReception.domain.HptReception;
import com.project.ihealme.userReservation.dto.UserReservationDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter @Getter
public class HptReceptionDto {

    private Long recNo;
    private UserReservationDto userReservationDto;
    private String currentStatus;
    private LocalDateTime regDate;
    private LocalDateTime updateRegDate;

    protected HptReceptionDto() {
    }

    private HptReceptionDto(Long recNo, String currentStatus, UserReservationDto userReservationDto, LocalDateTime regDate, LocalDateTime updateRegDate) {
        this.recNo = recNo;
        this.userReservationDto = userReservationDto;
        this.currentStatus = currentStatus;
        this.regDate = regDate;
        this.updateRegDate = updateRegDate;
    }

    private HptReceptionDto of(Long recNo, String currentStatus, UserReservationDto userReservationDto, LocalDateTime regDate, LocalDateTime updateRegDate) {
        return new HptReceptionDto(recNo, currentStatus, userReservationDto, regDate, updateRegDate);
    }

    public static HptReceptionDto from(HptReception entity) {
        return new HptReceptionDto(
                entity.getRecNo(),
                entity.getUserReservation().getCurrentStatus(),
                UserReservationDto.from(entity.getUserReservation()),
                entity.getUserReservation().getRegDate(),
                entity.getUserReservation().getUpdateRegDate()
        );
    }
}
