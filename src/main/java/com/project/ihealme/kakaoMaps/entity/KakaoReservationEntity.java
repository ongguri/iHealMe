package com.project.ihealme.kakaoMaps.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "kakaoReservation")
@Data
public class KakaoReservationEntity {

    @Id
    @Column(name = "res_id",nullable = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "pxName")
    private String pxName; // 환아명

    @Column(name = "txtList")
    private String txtList; // 진료항목

    @Column(name = "selectedPlaceName")
    private String selectedPlaceName; // 병원명

    public KakaoReservationEntity() {
    }

    public KakaoReservationEntity(String pxName, String txtList, String selectedPlaceName) {
        this.pxName = pxName;
        this.txtList = txtList;
        this.selectedPlaceName = selectedPlaceName;
    }

    public KakaoReservationEntity(Long id, String pxName, String txtList, String selectedPlaceName) {
        this.id = id;
        this.pxName = pxName;
        this.txtList = txtList;
        this.selectedPlaceName = selectedPlaceName;
    }
}
