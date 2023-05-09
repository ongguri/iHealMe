package com.project.ihealme.kakaoMaps.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.ihealme.kakaoMaps.service.KakaoMapsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

@RestController
//@Controller
@RequiredArgsConstructor
public class KakaoMapsController {

    private final KakaoMapsService kakaoMapsService;

    private final WebClient kakaoConfig;
    // @RequestParam : 자바스크립트에서 GET 또는 POST 방식으로 데이터를 전송하는 경우
    // @RequestBody : JSON 데이터를 전송하는 경우

    @GetMapping("/")
    @ResponseBody
    public String Maps() {
        return "maps/maps";
    }

    /*@GetMapping("/maps")
    public String Maps(@RequestParam String query) {
        Mono<String> mono = kakaoConfig.get()
                .uri(builder -> builder.path("/v2/local/search/keyword.json")
                        .queryParam("query", query)
                        .build()
                )
                .exchangeToMono(response -> {
                    return response.bodyToMono(String.class);
                });
        return mono.block();
    }*/

    @Value("${kakao.map.rest.api.key}")
    private String appkey;

    @GetMapping("/api")
    public String Maps1(@RequestParam("query") String query, Model model) {
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
        Map<String, Object> resultMap = new HashMap<>();

        try {
            resultMap = objectMapper.readValue(jsonData, new TypeReference<Map<String, Object>>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        model.addAttribute("resultMap", resultMap);
        // HTMl 템플릿을 렌더링하여 반환
        return "maps/maps1";
        //return resultMap;
    }
}
