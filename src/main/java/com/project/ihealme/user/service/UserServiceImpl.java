package com.project.ihealme.user.service;

import com.project.ihealme.user.dto.HospitalRequest;
import com.project.ihealme.user.dto.UserDTO;
import com.project.ihealme.user.dto.UserRequest;
import com.project.ihealme.user.entity.User;
import com.project.ihealme.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UsersService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final PasswordEncoder passwordEncoder;


    @Override
    public ResponseEntity<?> registerUser(UserRequest request) {
        checkRegister(request.getEmail(), request.getPassword(), request.getConfirmPassword());
        User user = new User(request, passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        return new ResponseEntity<>("회원가입 성공", HttpStatus.OK);
    }public ResponseEntity<?> registerHospital(HospitalRequest request) {
        checkRegister(request.getEmail(), request.getPassword(), request.getConfirmPassword());
        User hospital = new User(request, passwordEncoder.encode(request.getPassword()));
        userRepository.save(hospital);
        return new ResponseEntity<>("회원가입 성공", HttpStatus.OK);
    }

    private void checkRegister(String email, String request1, String confirmPassword) {
        Optional<User> usernameUserFound = userRepository.findByEmail(email);
        if (usernameUserFound.isPresent()) {
            throw new ResponseStatusException(HttpStatus.valueOf(409), "중복된 이메일이 존재합니다");
        }
        if (!request1.equals(confirmPassword)) {
            throw new IllegalArgumentException("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        }
    }


    @Override
    public UserDTO findUser(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new BadCredentialsException("회원 정보를 찾을 수 없습니다."));
        return UserDTO.builder()
                .userId(user.getUserId())
                .password(user.getPassword())
                .userRole(user.getUserRole())
                .email(user.getEmail())
                .build();
    }

    @Override
    public UserDTO findByEmailAndPassword(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new BadCredentialsException("이메일이나 비밀번호를 확인해주세요."));

        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
        }

        return UserDTO.builder()
                .userId(user.getUserId())
                .password(user.getPassword())
                .userRole(user.getUserRole())
                .email(user.getEmail())
                .build();
    }

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(
                        u -> UserDTO.builder().
                                userId(u.getUserId())
                                .password(u.getPassword())
                                .userRole(u.getUserRole())
                                .email(u.getEmail())
                                .build())
                .collect(Collectors.toList());
    }

}