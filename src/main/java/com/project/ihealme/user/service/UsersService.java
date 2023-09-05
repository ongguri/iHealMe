package com.project.ihealme.user.service;

import com.project.ihealme.user.dto.UserDTO;
import com.project.ihealme.user.dto.UserRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UsersService {
    ResponseEntity<?> registerUser(UserRequest userRequest);

    UserDTO findUser(String email);

    UserDTO findByEmailAndPassword(String email, String password);

    List<UserDTO> findAll();
}