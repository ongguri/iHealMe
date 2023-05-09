package com.project.ihealme.user.service;

import com.project.ihealme.user.dto.UserDTO;
import com.project.ihealme.user.dto.UserRequest;
import com.project.ihealme.user.entity.User;
import com.project.ihealme.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UsersService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public UserDTO createUser(UserRequest userRequest) {
        User user = userRepository.save(
                User.builder().password(bCryptPasswordEncoder.encode(userRequest.getPassword())).email(userRequest.getEmail()).userRole(userRequest.getUserRole()).build());
        return UserDTO.builder().id(user.getUserId()).password(user.getPassword()).userRole(user.getUserRole()).email(user.getEmail()).build();
    }

    @Override
    public UserDTO findUser(String email) {
        User user = Optional.ofNullable(userRepository.findByEmail(email)).orElseThrow(()->new BadCredentialsException("회원 정보를 찾을 수 없습니다."));
        return UserDTO.builder().id(user.getUserId()).password(user.getPassword()).userRole(user.getUserRole()).email(user.getEmail()).build();
    }

    @Override
    public UserDTO findByEmailAndPassword(String email, String password) {
        User user = Optional.ofNullable(userRepository.findByEmail(email)).orElseThrow(()->new BadCredentialsException("이메일이나 비밀번호를 확인해주세요."));

        if (bCryptPasswordEncoder.matches(password, user.getPassword()) == false) {
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
        }

        return UserDTO.builder().id(user.getUserId()).password(user.getPassword()).userRole(user.getUserRole()).email(user.getEmail()).build();
    }

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(u->UserDTO.builder().id(u.getUserId()).password(u.getPassword()).userRole(u.getUserRole()).email(u.getEmail()).build()).collect(Collectors.toList());
    }


}