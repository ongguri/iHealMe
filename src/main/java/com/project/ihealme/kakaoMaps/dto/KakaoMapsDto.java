package com.project.ihealme.kakaoMaps.dto;

import lombok.Data;


@Data
public class KakaoMapsDto {

    private String id;
    private String placeName;
    private String categoryName;
    private String categoryGroupCode;
    private String categoryGroupName;
    private String phone;
    private String addressName;
    private String roadAddressName;
    private String x;
    private String y;
    private String placeUrl;
    private String distance;

}
