package com.project.ihealme.HptReception.controller;

import com.project.ihealme.HptReception.domain.HptReception;
import com.project.ihealme.HptReception.dto.HptRecPageRequestDTO;
import com.project.ihealme.HptReception.service.HptReceptionService;
import com.project.ihealme.userReservation.service.UserReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HptReceptionController {

    @Autowired
    private UserReservationService userReservationService;

    @Autowired
    private HptReceptionService hptReceptionService;

    @GetMapping("/HptReception/HptReceptionList")
    public String hptReception(@ModelAttribute HptRecPageRequestDTO hptRecPageRequestDTO, Model model) {
        int rtCount = hptReceptionService.getRtCount();
        Map<String, String> searchTypes = new LinkedHashMap<>();
        searchTypes.put("tx", "진료항목");
        searchTypes.put("st", "상태");

        model.addAttribute("rtCount", rtCount);
        model.addAttribute("searchTypes", searchTypes);
        model.addAttribute("result", hptReceptionService.getUserResList(hptRecPageRequestDTO));
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
    public String subCounter(@RequestBody Map<String, Object> requestBody) {
        int rtCount = (int) requestBody.get("rtCount");
//        System.out.println("rtCount = " + rtCount);
        if(rtCount > 0) {
            hptReceptionService.decreaseRtCount();
        }
        return "success";
    }

    @GetMapping("/HptReception/HptReceptionList/updateCurrentStatusToAccept")       // <a> 태그는 get 방식으로 요청한다.
    public String updateCurrentStatusToAccept(@RequestParam("resNo") int resNo) {
        hptReceptionService.updateCurrentStatus(resNo, "진료 전", LocalDateTime.now());
        userReservationService.updateCurrentStatus(resNo, "진료 전");
        hptReceptionService.increaseRtCount(); // 대기자 수 +1

        return "redirect:/HptReception/HptReceptionList";
    }

    @GetMapping("/HptReception/HptReceptionList/updateCurrentStatusToReject")
    public String updateCurrentStatusToReject(@RequestParam("resNo") int resNo) {
        hptReceptionService.updateCurrentStatus(resNo, "접수취소", LocalDateTime.now());
        userReservationService.updateCurrentStatus(resNo, "접수취소");
        return "redirect:/HptReception/HptReceptionList";
    }

    @GetMapping("/HptReception/HptReceptionList/updateCurrentStatusToComplete")
    public String updateCurrentStatusToComplete(@RequestParam("resNo") int resNo) {
        hptReceptionService.updateCurrentStatus(resNo, "진료완료", LocalDateTime.now());
        userReservationService.updateCurrentStatus(resNo, "진료완료");
        hptReceptionService.decreaseRtCount(); // 대기자 수 -1

        return "redirect:/HptReception/HptReceptionList";
    }

}
