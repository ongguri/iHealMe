package com.project.ihealme;

import com.project.ihealme.HptReception.service.HptReceptionService;
import com.project.ihealme.userReservation.service.UserReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
public class commonController {

    private HptReceptionService hptReceptionService;

    public commonController(HptReceptionService hptReceptionService) {
        this.hptReceptionService = hptReceptionService;
    }

    @Autowired
    private UserReservationService userReservationService;

//    @RequestMapping(value = {"/userResCancelUpdate", "/HptReception/HptReceptionList/updateCurrentStatusToReject"})
//    public String cancelReception(@RequestParam("resNo") int resNo, HttpServletRequest request, UserReservation userReservation) {
//
//        String viewName = "";
//        String requestUrl = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
//        // 요청된 url 반환
//
//        if(requestUrl.equals("/userResCancelUpdate")) {
//            viewName = "redirect:/userReservation";
//        }
//        else if(requestUrl.equals("/HptReception/HptReceptionList/updateCurrentStatusToReject")){
//            viewName = "redirect:/HptReception/HptReceptionList";
//        }
//
//        hptReceptionService.updateCurrentStatus(resNo, "접수취소", LocalDateTime.now());
//        userReservationService.updateStatus(userReservation);
//
//
//        return viewName;
//    }

    @GetMapping("/HptReception/HptReceptionList/updateCurrentStatusToAccept")       // <a> 태그는 get 방식으로 요청한다.
    public String updateCurrentStatusToAccept(@RequestParam("resNo") int resNo) {
        hptReceptionService.updateCurrentStatus(resNo, "진료 전", LocalDateTime.now());
        userReservationService.updateStatusToAccept((long) resNo);
        hptReceptionService.increaseRtCount(); // 대기자 수 +1

        return "redirect:/HptReception/HptReceptionList";
    }
}
