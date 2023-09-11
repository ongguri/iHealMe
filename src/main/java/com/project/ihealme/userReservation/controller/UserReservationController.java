package com.project.ihealme.userReservation.controller;

import com.project.ihealme.HptReception.service.HptReceptionService;
import com.project.ihealme.community.dto.PostWriteRequestDTO;
import com.project.ihealme.user.entity.User;
import com.project.ihealme.userReservation.dto.request.UserResPageRequestDTO;
import com.project.ihealme.userReservation.service.UserReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class UserReservationController {

    @Autowired
    private UserReservationService userReservationService;

    @Autowired
    private HptReceptionService hptReceptionService;

    @GetMapping("/userReservation")
    public String userRes(@ModelAttribute UserResPageRequestDTO userResPageRequestDTO, Model model) {
        Map<String, String> searchTypes = new LinkedHashMap<>();
        searchTypes.put("hpt", "병원명");
        searchTypes.put("tx", "진료항목");
        searchTypes.put("st", "상태");

        model.addAttribute("result", userReservationService.getUserResList(userResPageRequestDTO));
        model.addAttribute("searchTypes", searchTypes);

        return "reservation/userReservation";
    }

    @GetMapping("/userResCancelUpdate")
    public String updateCurrentStatusToComplete(@RequestParam("resNo") Long resNo) {
        userReservationService.updateCurrentStatus(resNo, "접수취소");
//        hptReceptionService.decreaseRtCount(); // 대기자 수 -1

        return "redirect:/userReservation";
    }

    @GetMapping("/community/write")
    public String writePostPage(@AuthenticationPrincipal User user,
                                @RequestParam("resNo") Long resNo,
                                @RequestParam("name") String hptName,
                                Model model) {

        if (user == null) {
            return "redirect:/login";
        }

        model.addAttribute("postWriteReq", new PostWriteRequestDTO(user.getUserId(), resNo, hptName));
        return "community/writePost";
    }
}
