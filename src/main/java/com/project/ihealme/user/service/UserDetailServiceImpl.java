package com.project.ihealme.user.service;

import com.project.ihealme.user.entity.User;
import com.project.ihealme.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public User loadUserByUsername(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new BadCredentialsException("이메일 주소를 확인해주세요."));
    }

}