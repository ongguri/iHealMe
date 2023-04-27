package com.project.ihealme.HPTReception.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HPTReceptionController {
    @GetMapping("/HPTReception")
    public String HPTReceptionForm(){

        return "HPTReception";
    }
}
