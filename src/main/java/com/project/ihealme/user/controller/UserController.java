package com.project.ihealme.user.controller;

import com.project.ihealme.user.dto.HospitalRequest;
import com.project.ihealme.user.dto.UserDTO;
import com.project.ihealme.user.dto.UserRequest;
import com.project.ihealme.user.jwt.JwtConfig;
import com.project.ihealme.user.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl usersService;
    private final JwtConfig jwtConfig;

    @GetMapping("/login")
    public String login() {
        return "users/login";
    }

    @GetMapping("/type")
    public String chooseType() {
        return "users/usertype";
    }

    @GetMapping("/signupuser")
    public String signupUser() {
        return "users/signupuser";
    }

    @GetMapping("/signuphospital")
    public String signupHospital() {
        return "users/signuphospital";
    }

    @PostMapping(value = "/registeruser")
    public String registerUser(@RequestBody UserRequest userRequest) {
        usersService.registerUser(userRequest);
        return "redirect:/login";
    }

    @PostMapping(value = "/registerhospital")
    public String registerHospital(@RequestBody HospitalRequest hospitalRequest) {
        usersService.registerHospital(hospitalRequest);
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String login(@RequestBody UserDTO userDTO, RedirectAttributes redirectAttributes) {
        UserDTO user = usersService.findByEmailAndPassword(userDTO.getEmail(), userDTO.getPassword());
        String token = jwtConfig.createToken(user.getEmail(), Collections.singletonList(user.getUserRole().getValue()));
        redirectAttributes.addFlashAttribute("token",token);
        return "redirect:/";
    }

//    @GetMapping("/my")
//    public UserDTO findUser(Authentication authentication) {
//        if (authentication == null) {
//            throw new BadCredentialsException("회원 정보를 찾을 수 없습니다.");
//        }
//        return usersService.findUser(authentication.getName());
//    }

//    @GetMapping("/admin")
//    @PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
//    public List<UserDTO> findAllUser() {
//        return usersService.findAll();
//    }

}