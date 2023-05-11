package com.project.ihealme.user.controller;

import com.project.ihealme.user.dto.UserDTO;
import com.project.ihealme.user.dto.UserRequest;
import com.project.ihealme.user.jwt.JwtConfig;
import com.project.ihealme.user.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl usersService;
    private final JwtConfig jwtConfig;

    @GetMapping("/login")
    public String login() {
        return "/users/login";
    }

    @GetMapping("/type")
    public String chooseType() {
        return "/users/usertype";
    }

    @GetMapping("/signupuser")
    public String signupUser() {
        return "/users/signupuser";
    }

    @GetMapping("/signuphospital")
    public String signupHospital() {
        return "/users/signuphospital";
    }

    @PostMapping(value = "/registeruser")
    public String registerUser(@RequestBody UserRequest userRequest) {
        usersService.registerUser(userRequest);
        return "redirect:/users/login";
    }

    @GetMapping("/my")
    public UserDTO findUser(Authentication authentication) {
        if (authentication == null) {
            throw new BadCredentialsException("회원 정보를 찾을 수 없습니다.");
        }
        return usersService.findUser(authentication.getName());
    }

    @GetMapping("/admin")
    @PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
    public List<UserDTO> findAllUser() {
        return usersService.findAll();
    }

    @PostMapping("/login")
    public String login(UserRequest userRequest) {
        UserDTO users = usersService.findByEmailAndPassword(userRequest.getEmail(), userRequest.getPassword());
        return jwtConfig.createToken(users.getEmail(), Collections.singletonList(users.getUserRole().getValue()));
    }

}