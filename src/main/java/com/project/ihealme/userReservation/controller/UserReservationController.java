package com.project.ihealme.userReservation.controller;

import com.project.ihealme.HptReception.service.HptReceptionService;
import com.project.ihealme.community.dto.PostWriteRequestDTO;
import com.project.ihealme.user.entity.User;
import com.project.ihealme.userReservation.service.UserReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
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
    public String updateCurrentStatusToComplete(@RequestParam("resNo") int resNo) {
        userReservationService.updateCurrentStatus(resNo, "접수취소");
        hptReceptionService.updateCurrentStatus(resNo, "접수취소", LocalDateTime.now());
        hptReceptionService.decreaseRtCount(); // 대기자 수 -1

        return "redirect:/userReservation";
    }

//    @PostMapping("/userResCancelUpdate")
//    public String userResUpdate(UserReservation userReservation) {
//        Long user = userReservationService.updateStatus(userReservation);
////        System.out.println("user = " + user);
//        return "redirect:/userReservation";
//    }

    /*@GetMapping("/community/writePost")
    public String writePostPage() {
        return "community/writePost";
    }*/

    @GetMapping("/community/write")
    public String writePostPage(@AuthenticationPrincipal User user,
                                @RequestParam("resNo") Long resNo,
                                @RequestParam("name") String hptName,
                                Model model) {

        model.addAttribute("postWriteReq", new PostWriteRequestDTO(user.getUserId(), resNo, hptName));
        return "community/writePost";
    }
}
