package com.project.ihealme.kakaoMaps.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "maps")
@Getter @Setter
@ToString
public class KakaoMapsEntity {

    @Id
    @Column(name = "id", length = 15, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(name = "email", length = 50, nullable = false)
    private String email;

    @Column(name = "place_name", length = 100, nullable = false)
    private String placeName;

    @Column(name = "distance", length = 10, nullable = false)
    private String distance;

    @Column(name = "place_url", length = 100, nullable = false)
    private String placeUrl;

    @Column(name = "category_name", length = 200, nullable = false)
    private String categoryName;

    @Column(name = "address_name", length = 200, nullable = false)
    private String addressName;

    @Column(name = "road_address_name", length = 200, nullable = false)
    private String roadAddressName;

    @Column(name = "phone", length = 20, nullable = false)
    private String phone;

    @Column(name = "category_group_code", length = 10, nullable = false)
    private String categoryGroupCode;

    @Column(name = "category_group_name", length = 10, nullable = false)
    private String categoryGroupName;

    @Column(name = "x", length = 30, nullable = false)
    private String x;

    @Column(name = "y", length = 30, nullable = false)
    private String y;
}
