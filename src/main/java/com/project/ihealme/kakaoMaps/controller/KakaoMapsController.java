package com.project.ihealme.kakaoMaps.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.ihealme.kakaoMaps.entity.KakaoMapsEntity;
import com.project.ihealme.kakaoMaps.repository.KakaoMapsRepository;
import com.project.ihealme.kakaoMaps.service.KakaoMapsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@Controller
@Slf4j
@RequiredArgsConstructor
public class KakaoMapsController {

    private final KakaoMapsService kakaoMapsService;

    @Value("${kakao.map.rest.api.key}")
    private String appkey;

    /*@GetMapping("/api")
    @ResponseBody
    public String Maps1(@RequestParam String query) throws JsonProcessingException {
        // 카카오맵 API를 사용하여 JSON 데이터 받아오기
        String apiUrl = "https://dapi.kakao.com/v2/local/search/keyword.json?query=" + query;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK " + appkey);
        HttpEntity<String> entity = new HttpEntity<>("documents", headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, String.class);
        String jsonData = response.getBody();

        // JSON 데이터를 Map 형태로 변환하여 반환
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(jsonData);
        List<Map<String, Object>> resultList = new ArrayList<>();
        if (root.has("documents")) {
            JsonNode documents = root.get("documents");
            for (JsonNode document : documents) {
                Map<String, Object> resultMap = new HashMap<>();
                resultMap.put("id", document.get("id").asText());
                resultMap.put("place_name", document.get("place_name").asText());
                resultMap.put("phone", document.get("phone").asText());
                resultMap.put("road_address_name", document.get("road_address_name").asText());
                resultMap.put("place_url", document.get("place_url").asText());
                resultList.add(resultMap);
            }
        }
        //model.addAttribute("resultList", resultList);

        return resultList;
    }*/

    @GetMapping("/api")
    @ResponseBody
    public ResponseEntity<List<KakaoMapsEntity>> maps(String query) throws JsonProcessingException {
        List<KakaoMapsEntity> kakaoList = kakaoMapsService.selectKeyword(query);
        return new ResponseEntity<>(kakaoList, HttpStatus.OK);
    }
}
