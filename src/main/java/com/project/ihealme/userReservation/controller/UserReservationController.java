package com.project.ihealme.userReservation.controller;

import com.project.ihealme.userReservation.domain.UserReservation;
import com.project.ihealme.userReservation.service.UserReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserReservationController {

    @Autowired
    private UserReservationService userReservationService;

    @GetMapping("/")
    public String userRes(Model model) {

        List<UserReservation> reservations = userReservationService.findReservations();
        model.addAttribute("userReservationList", reservations);
        return "reservation/userReservation";
    }
}
