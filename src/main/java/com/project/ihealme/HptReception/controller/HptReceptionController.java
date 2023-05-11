package com.project.ihealme.HptReception.controller;

import com.project.ihealme.HptReception.domain.HptReception;
import com.project.ihealme.HptReception.service.HptReceptionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class HptReceptionController {

    private final HptReceptionService hptReceptionService;

    public HptReceptionController(HptReceptionService hptReceptionService) {
        this.hptReceptionService = hptReceptionService;
    }

    @GetMapping("/HptReception/HptReceptionList")
    public String hptReception(Model model) {
        int rtCount = hptReceptionService.getRtCount();
        model.addAttribute("rtCount", rtCount);
        List<HptReception> hptReceptionList = hptReceptionService.getHptReceptionList();
        model.addAttribute("hptReceptionList", hptReceptionList);
        return "HptReception/HptReceptionList";
    }

    @PostMapping("/HptReception/HptReceptionList/addCounter")
    @ResponseBody
    public String addCounter() {
        hptReceptionService.increaseRtCount();
        return "success";
    }

    @PostMapping("/HptReception/HptReceptionList/subCounter")
    @ResponseBody
    public String subCounter() {
        hptReceptionService.decreaseRtCount();
        return "success";
    }

//    @GetMapping("/HptReception/HptReceptionList/updateCurrentStatusToAccept")       // <a> 태그는 get 방식으로 요청한다.
//    public String updateCurrentStatusToAccept(@RequestParam("resNo") int resNo) {
//        hptReceptionService.updateCurrentStatus(resNo, "진료 전", LocalDateTime.now());
//        hptReceptionService.increaseRtCount(); // 대기자 수 +1
//
//        return "redirect:/HptReception/HptReceptionList";
//    }

//    @GetMapping("/HptReception/HptReceptionList/updateCurrentStatusToReject")
//    public String updateCurrentStatusToReject(@RequestParam("resNo") int resNo) {
//        hptReceptionService.updateCurrentStatus(resNo, "접수취소", LocalDateTime.now());
//
//        return "redirect:/HptReception/HptReceptionList";
//    }

    @GetMapping("/HptReception/HptReceptionList/updateCurrentStatusToComplete")
    public String updateCurrentStatusToComplete(@RequestParam("resNo") int resNo) {
        hptReceptionService.updateCurrentStatus(resNo, "진료완료", LocalDateTime.now());
        hptReceptionService.decreaseRtCount(); // 대기자 수 -1

        return "redirect:/HptReception/HptReceptionList";
    }

}
