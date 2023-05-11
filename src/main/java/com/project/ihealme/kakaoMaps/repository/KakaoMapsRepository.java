package com.project.ihealme.kakaoMaps.repository;

import com.project.ihealme.kakaoMaps.entity.KakaoMapsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface KakaoMapsRepository extends JpaRepository<KakaoMapsEntity, Long> {

    List<KakaoMapsEntity> findById(String id);
    List<KakaoMapsEntity> findByPlaceName(String placeName);
}
