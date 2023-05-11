package com.project.ihealme.kakaoMaps.entity;

import com.project.ihealme.kakaoMaps.dto.KakaoMapsDto;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "maps")
@Data
public class KakaoMapsEntity {

    @Id
    @Column(name = "id", length = 15, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(name = "place_name", length = 100, nullable = false)
    private String placeName;

    @Column(name = "phone", length = 20, nullable = false)
    private String phone;

    @Column(name = "road_address_name", length = 200, nullable = false)
    private String roadAddressName;

    @Column(name = "place_url", length = 100, nullable = false)
    private String placeUrl;

    public KakaoMapsEntity() {
    }

    public void updateKakao(KakaoMapsDto kakaoMapsDto) {
        this.id = kakaoMapsDto.getId();
        this.placeName = kakaoMapsDto.getPlaceName();
        this.phone = kakaoMapsDto.getPhone();
        this.roadAddressName = kakaoMapsDto.getRoadAddressName();
        this.placeUrl = kakaoMapsDto.getPlaceUrl();
    }

    public KakaoMapsEntity(String id, String placeName, String phone, String roadAddressName, String placeUrl) {
        this.id = id;
        this.placeName = placeName;
        this.phone = phone;
        this.roadAddressName = roadAddressName;
        this.placeUrl = placeUrl;
    }

    public KakaoMapsEntity(String id, String placeName) {
        this.id = id;
        this.placeName = placeName;
    }
}

