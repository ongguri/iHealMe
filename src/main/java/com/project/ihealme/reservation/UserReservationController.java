package com.project.ihealme.reservation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserReservationController {

    @GetMapping("/")
    public String userRes() {
        return "reservation/userReservation";
    }
}
