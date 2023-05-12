package com.project.ihealme.kakaoMaps.dto;

import com.project.ihealme.kakaoMaps.entity.KakaoMapsEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@Data
@NoArgsConstructor
public class KakaoMapsDto {

    private String id;
    private String placeName;
    private String phone;
    private String roadAddressName;
    private String placeUrl;

    @Builder
    public KakaoMapsDto(String id, String placeName, String phone, String roadAddressName, String placeUrl) {
        this.id = id;
        this.placeName = placeName;
        this.phone = phone;
        this.roadAddressName = roadAddressName;
        this.placeUrl = placeUrl;
    }

    public KakaoMapsEntity toEntity() {
        return KakaoMapsEntity.builder()
                .id(id)
                .placeName(placeName)
                .phone(phone)
                .roadAddressName(roadAddressName)
                .placeUrl(placeUrl)
                .build();
    }
}
