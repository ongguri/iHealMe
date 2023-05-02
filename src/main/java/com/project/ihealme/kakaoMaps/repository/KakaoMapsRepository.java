package com.project.ihealme.kakaoMaps.repository;

import com.project.ihealme.kakaoMaps.entity.KakaoMapsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KakaoMapsRepository extends JpaRepository<KakaoMapsEntity, Long> {

    KakaoMapsEntity findById(String id);
}
