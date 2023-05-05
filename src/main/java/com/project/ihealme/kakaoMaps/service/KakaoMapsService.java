package com.project.ihealme.kakaoMaps.service;

import com.project.ihealme.kakaoMaps.dto.KakaoMapsDto;
import com.project.ihealme.kakaoMaps.entity.KakaoMapsEntity;
import com.project.ihealme.kakaoMaps.repository.KakaoMapsRepository;
import org.springframework.stereotype.Service;

@Service
public class KakaoMapsService {

    private final KakaoMapsRepository kakaoMapsRepository;

    public KakaoMapsService(KakaoMapsRepository kakaoMapsRepository) {
        this.kakaoMapsRepository = kakaoMapsRepository;
    }

    // 카카오맵 API에서 가져온 정보를 KakaoMapsEntity로 저장하는 메서드
    public static void saveKakaoMaps(KakaoMapsDto kakaoMapsDto) {
        KakaoMapsEntity kakaoMapsEntity = new KakaoMapsEntity();
        kakaoMapsEntity.setId(kakaoMapsDto.getId());
        kakaoMapsEntity.setRepEmail(kakaoMapsDto.getRepEmail());
        kakaoMapsEntity.setPlaceName(kakaoMapsDto.getPlaceName());
        kakaoMapsEntity.setDistance(kakaoMapsDto.getDistance());
        kakaoMapsEntity.setPlaceUrl(kakaoMapsDto.getPlaceUrl());
        kakaoMapsEntity.setCategoryName(kakaoMapsDto.getCategoryName());
        kakaoMapsEntity.setAddressName(kakaoMapsDto.getAddressName());
        kakaoMapsEntity.setRoadAddressName(kakaoMapsDto.getRoadAddressName());
        kakaoMapsEntity.setPhone(kakaoMapsDto.getPhone());
        kakaoMapsEntity.setCategoryGroupCode(kakaoMapsDto.getCategoryGroupCode());
        kakaoMapsEntity.setCategoryGroupName(kakaoMapsDto.getCategoryGroupName());
        kakaoMapsEntity.setX(kakaoMapsDto.getX());
        kakaoMapsEntity.setY(kakaoMapsDto.getY());
    }
}
