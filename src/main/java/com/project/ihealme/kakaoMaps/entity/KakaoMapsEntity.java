package com.project.ihealme.kakaoMaps.entity;

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

    @Column(name = "category_name", length = 200, nullable = false)
    private String categoryName;

    @Column(name = "category_group_code", length = 10, nullable = false)
    private String categoryGroupCode;

    @Column(name = "category_group_name", length = 10, nullable = false)
    private String categoryGroupName;

    @Column(name = "phone", length = 20, nullable = false)
    private String phone;

    @Column(name = "address_name", length = 200, nullable = false)
    private String addressName;

    @Column(name = "road_address_name", length = 200, nullable = false)
    private String roadAddressName;

    @Column(name = "x", length = 30, nullable = false)
    private String x;

    @Column(name = "y", length = 30, nullable = false)
    private String y;

    @Column(name = "place_url", length = 100, nullable = false)
    private String placeUrl;

    @Column(name = "distance", length = 10, nullable = false)
    private String distance;

    public KakaoMapsEntity() {
    }

    public KakaoMapsEntity(String id, String placeName, String categoryName, String categoryGroupCode,
                           String categoryGroupName, String phone, String addressName, String roadAddressName,
                           String x, String y, String placeUrl, String distance) {
        this.id = id;
        this.placeName = placeName;
        this.categoryName = categoryName;
        this.categoryGroupCode = categoryGroupCode;
        this.categoryGroupName = categoryGroupName;
        this.phone = phone;
        this.addressName = addressName;
        this.roadAddressName = roadAddressName;
        this.x = x;
        this.y = y;
        this.placeUrl = placeUrl;
        this.distance = distance;
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

