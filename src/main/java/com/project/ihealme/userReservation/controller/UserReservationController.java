package com.project.ihealme.userReservation.controller;

import com.project.ihealme.HptReception.service.HptReceptionService;
import com.project.ihealme.userReservation.domain.UserReservation;
import com.project.ihealme.userReservation.service.UserReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class UserReservationController {

    @Autowired
    private UserReservationService userReservationService;

    @Autowired
    private HptReceptionService hptReceptionService;

    @GetMapping("/userReservation")
    public String userRes(Model model, HttpSession session) {

        List<UserReservation> reservations = userReservationService.getUserReservationList();
//        model.addAttribute("userReservationList", reservations);
        session.setAttribute("userReservationList", reservations);
        return "reservation/userReservation";
    }

    @GetMapping("/userResCancelUpdate")
    public String updateCurrentStatusToComplete(@RequestParam("resNo") int resNo) {
        userReservationService.updateCurrentStatus(resNo, "접수취소", LocalDateTime.now());
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
    public String writePostPage(@RequestParam("resNo") int resNo, @RequestParam("name") String hptName, Model model) {
//        System.out.println("resNo = " + resNo);
//        System.out.println("hptName = " + hptName);

        model.addAttribute("resNo", resNo);
        model.addAttribute("hptName", hptName);

        return "community/writePost";
    }
}
