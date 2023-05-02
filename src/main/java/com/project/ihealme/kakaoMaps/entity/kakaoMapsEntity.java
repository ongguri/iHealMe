package com.project.ihealme.kakaoMaps.entity;

import javax.persistence.*;

@Entity
public class kakaoMapsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "rep_Email")
    private String repEmail;

    @Column(name = "place_name")
    private String placeName;

    @Column(name = "distance")
    private String distance;

    @Column(name = "place_url")
    private String placeUrl;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "address_name")
    private String addressName;

    @Column(name = "road_address_name")
    private String roadAddressName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "category_group_code")
    private String categoryGroupCode;

    @Column(name = "category_group_name")
    private String categoryGroupName;

    @Column(name = "x")
    private String x;

    @Column(name = "y")
    private String y;
}
