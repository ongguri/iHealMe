package com.project.ihealme.kakaoMaps.repository;

import com.project.ihealme.kakaoMaps.entity.KakaoReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KakaoReservationRepository extends JpaRepository<KakaoReservationEntity, Long> {

}
