package com.project.ihealme.HptReception.controller;

import com.project.ihealme.HptReception.dto.request.HptRecPageRequestDTO;
import com.project.ihealme.HptReception.dto.request.HptReceptionRequest;
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

    @PostMapping("/HptReceptionList/updateCurrentStatus")       // <a> 태그는 get 방식으로 요청한다.(9/13일: <a>태그에서 <form> 태그로 수정)
    public String updateCurrentStatusToAccept(
        @RequestParam("recNo") Long recNo,
        HptReceptionRequest hptReceptionRequest
    ) {
        hptReceptionService.updateCurrentStatus(recNo, hptReceptionRequest.toDto());
//        hptReceptionService.increaseRtCount(); // 대기자 수 +1

        return "redirect:/HptReception/HptReceptionList";
    }

//    @PostMapping("/HptReceptionList/updateCurrentStatusToReject")
//    public String updateCurrentStatusToReject(
//        @RequestParam("recNo") Long recNo,
//        HptReceptionRequest hptReceptionRequest
//    ) {
//        hptReceptionService.updateCurrentStatus(recNo, hptReceptionRequest.toDto());
//        return "redirect:/HptReception/HptReceptionList";
//    }
//
//    @PostMapping("/HptReceptionList/updateCurrentStatusToComplete")
//    public String updateCurrentStatusToComplete(
//        @RequestParam("recNo") Long recNo,
//        HptReceptionRequest hptReceptionRequest
//    ) {
//        hptReceptionService.updateCurrentStatus(recNo, hptReceptionRequest.toDto());
//        hptReceptionService.decreaseRtCount(); // 대기자 수 -1
//
//        return "redirect:/HptReception/HptReceptionList";
//    }

}
