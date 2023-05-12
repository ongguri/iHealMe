package com.project.ihealme.kakaoMaps.controller;

import com.project.ihealme.kakaoMaps.dto.KakaoMapsDto;
import com.project.ihealme.kakaoMaps.service.KakaoMapsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
//@RestController
@RequiredArgsConstructor
public class KakaoController {

    private final KakaoMapsService kakaoMapsService;

    /*@PostMapping("/")
    public String maps(@RequestBody KakaoMapsDto kakaoMapsDto) {
        return kakaoMapsService.save(kakaoMapsDto);
    }*/

    @GetMapping("/")
    public String maps() {
        return "maps/kakaoMaps";
    }


}
