package com.project.ihealme.kakaoMaps.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class KakaoController {

    @GetMapping("/")
    public String maps() {
        return "maps/kakaoMaps";
    }


}
