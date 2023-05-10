package com.project.ihealme;

import com.project.ihealme.HptReception.service.HptReceptionService;
import com.project.ihealme.userReservation.domain.UserReservation;
import com.project.ihealme.userReservation.service.UserReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Controller
public class commonController {

    private HptReceptionService hptReceptionService;

    public commonController(HptReceptionService hptReceptionService) {
        this.hptReceptionService = hptReceptionService;
    }

    @Autowired
    private UserReservationService userReservationService;

    @RequestMapping(value = {"/userResCancelUpdate", "/HptReception/HptReceptionList/updateCurrentStatusToReject"})
    public String cancelReception(@RequestParam("resNo") int resNo, HttpServletRequest request, UserReservation userReservation) {

        String viewName = "";
        String requestUrl = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        // 요청된 url 반환

        if(requestUrl.equals("/userResCancelUpdate")) {
            viewName = "redirect:/userReservation";
        }
        else if(requestUrl.equals("/HptReception/HptReceptionList/updateCurrentStatusToReject")){
            viewName = "redirect:/HptReception/HptReceptionList";
        }

        hptReceptionService.updateCurrentStatus(resNo, "접수취소", LocalDateTime.now());
        userReservationService.updateStatus(userReservation);

        return viewName;
    }
}
