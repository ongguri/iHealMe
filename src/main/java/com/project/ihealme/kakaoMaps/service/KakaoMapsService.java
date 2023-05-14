package com.project.ihealme.kakaoMaps.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.ihealme.kakaoMaps.config.KakaoConfig;
import com.project.ihealme.kakaoMaps.dto.KakaoMapsDto;
import com.project.ihealme.kakaoMaps.entity.KakaoMapsEntity;
import com.project.ihealme.kakaoMaps.repository.KakaoMapsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KakaoMapsService {

    @Autowired
    private KakaoConfig kakaoConfig;

    private final KakaoMapsRepository kakaoMapsRepository;

    public List<KakaoMapsDto> convertToKakaoMapsDto(String search) throws JsonProcessingException {
        // 카카오 API 호출하여 검색 결과를 받아옴
        String apiUrl = "https://dapi.kakao.com/v2/local/search/keyword.json?query=" + search;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK 0a931d3e53412cb779f034fc86ec4c96");
        HttpEntity<String> entity = new HttpEntity<>("body", headers);
        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, String.class);

        // 검색 결과를 리스트로 변환하여 저장
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        List<KakaoMapsDto> kakaoList = new ArrayList<>();
        if (root.has("documents")) {
            JsonNode documents = root.get("documents");
            for (JsonNode document : documents) {
                Long id = Long.valueOf(document.get("id").asText());
                String placeName = document.get("place_name").asText();
                String phone = document.get("phone").asText();
                String roadAddressName = document.get("road_address_name").asText();
                String placeUrl = document.get("place_url").asText();
                String x = document.get("x").asText();
                String y = document.get("y").asText();
                KakaoMapsDto list = new KakaoMapsDto(id, placeName, phone, roadAddressName, placeUrl, x, y);
                kakaoList.add(list);
            }
        }
        return kakaoList;
    }

    public List<KakaoMapsEntity> convertToKakaoMapsEntity(List<KakaoMapsDto> dtos) {
        return dtos.stream()
                .map(dto -> new KakaoMapsEntity(dto.getId(), dto.getPlaceName(), dto.getPhone(),
                        dto.getRoadAddressName(), dto.getPlaceUrl(), dto.getX(), dto.getY()))
                .collect(Collectors.toList());
    }

    public void saveAllPlaces(List<KakaoMapsDto> dtos) {
        List<KakaoMapsEntity> entities = convertToKakaoMapsEntity(dtos);
        kakaoMapsRepository.saveAll(entities);
    }

    public List<KakaoMapsEntity> getAll() {
        return kakaoMapsRepository.findAll();
    }

    public boolean checkIfDataExist() {
        long count = kakaoMapsRepository.count();
        return count > 0;
    }

    public void deleteAllPlaces() {
        kakaoMapsRepository.deleteAll();
    }
}
