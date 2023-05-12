package com.project.ihealme.kakaoMaps.dto;

import com.project.ihealme.kakaoMaps.entity.KakaoMapsEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
public class KakaoMapsDto {

    private String id;
    private String placeName;
    private String phone;
    private String roadAddressName;
    private String placeUrl;

    public KakaoMapsDto(String id, String placeName, String phone, String roadAddressName, String placeUrl) {
        this.id = id;
        this.placeName = placeName;
        this.phone = phone;
        this.roadAddressName = roadAddressName;
        this.placeUrl = placeUrl;
    }
}
