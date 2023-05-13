package com.project.ihealme.kakaoMaps.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Table(name = "maps")
@Getter @ToString
@AllArgsConstructor
@NoArgsConstructor
public class KakaoMapsEntity {

    @Id
    @Column(name = "id", length = 15, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String id;

    @Column(name = "place_name", length = 100, nullable = false)
    private String placeName;

    @Column(name = "phone", length = 20, nullable = false)
    private String phone;

    @Column(name = "road_address_name", length = 200, nullable = false)
    private String roadAddressName;

    @Column(name = "place_url", length = 100, nullable = false)
    private String placeUrl;
}

