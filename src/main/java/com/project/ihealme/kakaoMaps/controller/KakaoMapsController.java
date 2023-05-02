package com.project.ihealme.kakaoMaps.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class KakaoMapsController {

    @GetMapping("/maps")
    public String Maps() {
        return "maps/kakaoMaps";
    }
}
