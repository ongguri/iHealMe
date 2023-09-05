package com.project.ihealme.kakaoMaps.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class KakaoReservationDto {

    private Long id;

    @NotNull(message = "환아명은 필수 입력입니다.")
    private String pxName;
    @NotNull(message = "진료항목은 필수 입력입니다.")
    private String txtList;
    private String selectedPlaceName;
}
