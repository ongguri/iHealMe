package com.project.ihealme.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class kakaoAPI {

    /*@GetMapping("/maps")
    public String Maps() {
        return "maps/kakaoAPI";
    }*/

    @GetMapping("/")
    public String maps1() {
        return "maps/kakaoMaps";
    }
}
