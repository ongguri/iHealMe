package com.project.ihealme.HptReception.controller;

import com.project.ihealme.HptReception.dto.request.HptRecPageRequestDTO;
import com.project.ihealme.HptReception.service.HptReceptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
@RequiredArgsConstructor
@RequestMapping("/HptReception")
@Controller
public class HptReceptionController {

    private final HptReceptionService hptReceptionService;

    @GetMapping("/HptReceptionList")
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

    @PostMapping("/HptReceptionList/addCounter")
    @ResponseBody
    public String addCounter() {
        hptReceptionService.increaseRtCount();
        return "success";
    }

    @PostMapping("/HptReceptionList/subCounter")
    @ResponseBody
    public String subCounter(@RequestBody Map<String, Object> requestBody) {
        int rtCount = (int) requestBody.get("rtCount");
//        System.out.println("rtCount = " + rtCount);
        if(rtCount > 0) {
            hptReceptionService.decreaseRtCount();
        }
        return "success";
    }

    @GetMapping("/HptReceptionList/updateCurrentStatusToAccept")       // <a> 태그는 get 방식으로 요청한다.
    public String updateCurrentStatusToAccept(@RequestParam("recNo") Long recNo) {
        hptReceptionService.updateCurrentStatus(recNo, "진료 전", LocalDateTime.now());
        hptReceptionService.increaseRtCount(); // 대기자 수 +1

        return "redirect:/HptReception/HptReceptionList";
    }

    @GetMapping("/HptReceptionList/updateCurrentStatusToReject")
    public String updateCurrentStatusToReject(@RequestParam("recNo") Long recNo) {
        hptReceptionService.updateCurrentStatus(recNo, "접수취소", LocalDateTime.now());
        return "redirect:/HptReception/HptReceptionList";
    }

    @GetMapping("/HptReceptionList/updateCurrentStatusToComplete")
    public String updateCurrentStatusToComplete(@RequestParam("recNo") Long recNo) {
        hptReceptionService.updateCurrentStatus(recNo, "진료완료", LocalDateTime.now());
        hptReceptionService.decreaseRtCount(); // 대기자 수 -1

        return "redirect:/HptReception/HptReceptionList";
    }

}
