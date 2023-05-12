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
@Slf4j
@RequiredArgsConstructor
public class KakaoMapsController {

    /*private final KakaoMapsService kakaoMapsService;

    @GetMapping("/api")
    @ResponseBody
    public ResponseEntity<List<KakaoMapsEntity>> selectMaps(String query) throws JsonProcessingException {
        List<KakaoMapsEntity> kakaoList = kakaoMapsService.selectKeyword(query);
        return new ResponseEntity<>(kakaoList, HttpStatus.OK);
    }

    @GetMapping("/api/save")
    @ResponseBody
    public ResponseEntity<String> saveMaps(String query) throws JsonProcessingException {
        kakaoMapsService.saveKeyword(query);
        return ResponseEntity.ok("Success");
    }*/
}
