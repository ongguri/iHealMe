package com.project.ihealme.kakaoMaps.controller;

import com.project.ihealme.kakaoMaps.service.KakaoMapsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class KakaoMapsController {

    private final KakaoMapsService kakaoMapsService;

    // @RequestParam : 자바스크립트에서 GET 또는 POST 방식으로 데이터를 전송하는 경우
    // @RequestBody : JSON 데이터를 전송하는 경우

    @GetMapping("/")
    public String Maps() {
        return "maps/kakaoMaps";
    }
}
