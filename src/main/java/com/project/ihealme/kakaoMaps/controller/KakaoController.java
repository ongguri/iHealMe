package com.project.ihealme.kakaoMaps.controller;

import com.project.ihealme.kakaoMaps.service.KakaoMapsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
//@RestController
@RequiredArgsConstructor
public class KakaoController {

    private final KakaoMapsService kakaoMapsService;

    /*@PostMapping("/")
    public String maps(@RequestBody KakaoMapsDto kakaoMapsDto) {
        return kakaoMapsService.save(kakaoMapsDto);
    }*/

    /*@GetMapping("/")
    public String maps(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        if (inputFlashMap != null) {
            String token = (String) inputFlashMap.get("token");
            response.addCookie(new Cookie("token", token));
        }

        return "maps/kakaoMaps";
    }*/


}
