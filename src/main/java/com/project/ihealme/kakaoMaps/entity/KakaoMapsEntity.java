package com.project.ihealme.kakaoMaps.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Table(name = "maps")
@Getter @Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class KakaoMapsEntity {

    @Id
    @Column(name = "id", length = 15, nullable = false)
    private Long id;

    @Column(name = "place_name", length = 100, nullable = false)
    private String placeName;

    @Column(name = "phone", length = 20, nullable = false)
    private String phone;

    @Column(name = "road_address_name", length = 200, nullable = false)
    private String roadAddressName;

    @Column(name = "place_url", length = 100, nullable = false)
    private String placeUrl;

    @Column(name = "x", length = 100, nullable = false)
    private String x;

    @Column(name = "y", length = 100, nullable = false)
    private String y;

    /*@JoinColumn(name = "HPTRECEPTION_pName")
    private String pName; // HPTRECEPTION 테이블의 환아명

    @JoinColumn(name = "HPTRECEPTION_TXLIST")
    private String txList; // HPTRECEPTION 테이블의 진료항목

    @JoinColumn(name = "HPTRECEPTION_RTCOUNT")
    private int rtCount; // HPTRECEPTION 테이블의 실시간 대기자 수

    @JoinColumn(name = "USERRESERVATION_NAME")
    private String name; // USERRESERVATION 테이블의 병원명

    @JoinColumn(name = "USERRESERVATION_LIST")
    private String list; // USERRESERVATION 테이블의 진료항목*/

}

