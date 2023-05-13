package com.project.ihealme.kakaoMaps.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.ihealme.kakaoMaps.dto.KakaoMapsDto;
import com.project.ihealme.kakaoMaps.entity.KakaoMapsEntity;
import com.project.ihealme.kakaoMaps.repository.KakaoMapsRepository;
import com.project.ihealme.kakaoMaps.service.KakaoMapsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//@RestController
@Controller
@Slf4j
@RequiredArgsConstructor
public class KakaoMapsController {

    private final KakaoMapsService kakaoMapsService;


    /*@GetMapping("/api")
    @ResponseBody
    public ResponseEntity<List<Map<String, Object>> selectMaps(String query) throws JsonProcessingException {
        List<KakaoMapsDto> kakaoList = kakaoMapsService;

        // 필요한 필드만 추출하여 List<Map> 객체로 변환
        List<Map<String, Object>> resultList = kakaoList.stream().map(entity -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", entity.getId());
            map.put("placeName", entity.getPlaceName());
            map.put("phone", entity.getPhone());
            map.put("roadAddressName", entity.getRoadAddressName());
            map.put("placeUrl", entity.getPlaceUrl());
            return map;
        }).collect(Collectors.toList());

        return new ResponseEntity<>(resultList.httpStatus.OK);
    }

    /*@GetMapping("/api/search")
    public void search() {
    }*/

    /*@GetMapping("/api")
    public List<KakaoMapsDto> getPlaces() throws JsonProcessingException {
        return kakaoMapsService.convertToKakaoMapsEntity();
    }*/

    @GetMapping("/api")
    public String searchPlace() {
        return "maps/main";
    }

    @GetMapping("/api/places")
    public String placeList(@RequestParam String search, Model model) throws JsonProcessingException {
        System.out.println("search: " + search);
        List<KakaoMapsDto> places = kakaoMapsService.convertToKakaoMapsEntity(search);
        model.addAttribute("places", places);
        return "maps/searchList";
    }

    /*@PostMapping("/api")
    public String setPlace(KakaoMapsDto kakaoMapsDto) {
        KakaoMapsDto kakaoMapsDto1 = new KakaoMapsDto();
        kakaoMapsDto1.setPlaceName(kakaoMapsDto.getPlaceName());
        kakaoMapsDto1.setPhone(kakaoMapsDto.getPhone());
        kakaoMapsDto1.setRoadAddressName(kakaoMapsDto.getRoadAddressName());
        kakaoMapsDto1.setPlaceUrl(kakaoMapsDto.getPlaceUrl());

        kakaoMapsService.save(kakaoMapsDto1);
        return "redirect:/";
    }*/

    @GetMapping("/api/reservation")
    public String kakaoreservation() {
        return "maps/reservation";
    }
}