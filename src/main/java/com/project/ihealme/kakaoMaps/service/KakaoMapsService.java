package com.project.ihealme.kakaoMaps.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.ihealme.kakaoMaps.entity.KakaoMapsEntity;
import com.project.ihealme.kakaoMaps.repository.KakaoMapsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class KakaoMapsService {

    @Autowired
    private KakaoMapsRepository kakaoMapsRepository;

    @Transactional
    public void saveKeyword(@RequestParam String query) throws JsonProcessingException {
        // 카카오 API 호출하여 검색 결과를 받아옴
        String apiUrl = "https://dapi.kakao.com/v2/local/search/keyword.json?query=" + query;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK {0a931d3e53412cb779f034fc86ec4c96}");
        HttpEntity<String> entity = new HttpEntity<>("body", headers);
        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, String.class);

        // 검색 결과를 리스트로 변환하여 저장
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        List<KakaoMapsEntity> kakaoList = new ArrayList<>();
        if (root.has("documents")) {
            JsonNode documents = root.get("documents");
            for (JsonNode document : documents) {
                String id = document.get("id").asText();
                String placeName = document.get("place_name").asText();
                String phone = document.get("phone").asText();
                String roadAddressName = document.get("road_address_name").asText();
                String placeUrl = document.get("place_url").asText();
                KakaoMapsEntity list = new KakaoMapsEntity(id, placeName, phone, roadAddressName, placeUrl);
                kakaoList.add(list);
            }
        }



        /*// 검색 결과를 병원 테이블에 저장
        kakaoMapsRepository.findByAll(kakaoList);

        // repemail에 장소 ID 값을 저장
        for (Hospital hospital : hospitalList) {
            hospital.setRepemail(hospital.getPlaceId());
        }*/
    }

}
