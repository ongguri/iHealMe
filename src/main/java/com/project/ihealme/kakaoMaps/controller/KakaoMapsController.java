package com.project.ihealme.kakaoMaps.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

//@RestController
@Controller
@Slf4j
@RequiredArgsConstructor
public class KakaoMapsController {

//    private final KakaoMapsService kakaoMapsService;
//    private final KakaoMapsRepository kakaoMapsRepository;
//    private final KakaoReservationRepository kakaoReservationRepository;
//
//
//    /*@GetMapping("/api")
//    @ResponseBody
//    public ResponseEntity<List<Map<String, Object>> selectMaps(String query) throws JsonProcessingException {
//        List<KakaoMapsDto> kakaoList = kakaoMapsService;
//
//        // 필요한 필드만 추출하여 List<Map> 객체로 변환
//        List<Map<String, Object>> resultList = kakaoList.stream().map(entity -> {
//            Map<String, Object> map = new HashMap<>();
//            map.put("id", entity.getId());
//            map.put("placeName", entity.getPlaceName());
//            map.put("phone", entity.getPhone());
//            map.put("roadAddressName", entity.getRoadAddressName());
//            map.put("placeUrl", entity.getPlaceUrl());
//            return map;
//        }).collect(Collectors.toList());
//
//        return new ResponseEntity<>(resultList.httpStatus.OK);
//    }
//
//    /*@GetMapping("/api/search")
//    public void search() {
//    }*/
//
//    /*@GetMapping("/api")
//    public List<KakaoMapsDto> getPlaces() throws JsonProcessingException {
//        return kakaoMapsService.convertToKakaoMapsEntity();
//    }*/
//
//    @GetMapping("/")
//    public String searchPlace() {
//        return "maps/main";
//    }
//
//    @GetMapping("/places")
//    public String placeList(@RequestParam String search, Model model) throws JsonProcessingException {
//        List<KakaoMapsDto> places = kakaoMapsService.convertToKakaoMapsDto(search);
//        boolean isDataExist = kakaoMapsService.checkIfDataExist();
//
//        if (isDataExist) {
//            kakaoMapsService.deleteAllPlaces();
//        }
//        kakaoMapsService.saveAllPlaces(places);
//
//        List<KakaoMapsEntity> entities = kakaoMapsService.convertToKakaoMapsEntity(places);
//
//        model.addAttribute("places", entities);
//        return "maps/searchList";
//    }
//
//    @GetMapping("/places/map")
//    public String map(Model model) {
//        List<KakaoMapsEntity> entities = kakaoMapsService.getAll();
//        model.addAttribute("places", entities);
//        return "maps/maps";
//    }
//
//    /*@PostMapping("/api")
//    public String setPlace(KakaoMapsDto kakaoMapsDto) {
//        KakaoMapsDto kakaoMapsDto1 = new KakaoMapsDto();
//        kakaoMapsDto1.setPlaceName(kakaoMapsDto.getPlaceName());
//        kakaoMapsDto1.setPhone(kakaoMapsDto.getPhone());
//        kakaoMapsDto1.setRoadAddressName(kakaoMapsDto.getRoadAddressName());
//        kakaoMapsDto1.setPlaceUrl(kakaoMapsDto.getPlaceUrl());
//
//        kakaoMapsService.save(kakaoMapsDto1);
//        return "redirect:/";
//    }*/
//
//    @GetMapping("/reservation")
//    public String kakaoreservation(Model model) {
//        Long id = new Long(1);
//        String pxName = new String();
//        String selectedPlaceName = new String();
//        List<String> options = new ArrayList<String>();
//        options.add("소아진료");
//        options.add("영유아검진");
//        options.add("예방접종");
//        model.addAttribute("id", id);
//        model.addAttribute("pxName", pxName);
//        model.addAttribute("options", options);
//        model.addAttribute("selectedPlaceName", selectedPlaceName);
//        return "maps/reservation";
//    }
//
//    @PostMapping("/reservation")
//    @ResponseBody
//    public void saveReservation(@ModelAttribute KakaoReservationDto kakaoReservationDt0) {
//        /*KakaoReservationDto reservationDto = new KakaoReservationDto();
//        kakaoReservationDto.setId(reservationDto.getId());
//        kakaoReservationDto.setPxName(reservationDto.getPxName());
//        kakaoReservationDto.setTxtList(reservationDto.getTxtList());*/
//        kakaoMapsService.saveReservation(kakaoReservationDt0);
//    }
}
