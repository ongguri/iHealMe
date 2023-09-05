package com.project.ihealme.kakaoMaps.dto;

import com.project.ihealme.kakaoMaps.entity.KakaoMapsEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
public class KakaoMapsDto {

    private Long id;
    private String placeName;
    private String phone;
    private String roadAddressName;
    private String placeUrl;
    private String x;
    private String y;

    public KakaoMapsDto(Long id, String placeName, String phone, String roadAddressName, String placeUrl, String x, String y) {
        this.id = id;
        this.placeName = placeName;
        this.phone = phone;
        this.roadAddressName = roadAddressName;
        this.placeUrl = placeUrl;
        this.x = x;
        this.y = y;
    }
}
