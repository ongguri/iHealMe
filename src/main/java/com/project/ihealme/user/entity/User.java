package com.project.ihealme.user.entity;

import com.project.ihealme.user.dto.UserRequest;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "USERS")
@Entity
public class User implements UserDetails {

    // 이름, 이메일, 연락처, 생년월일, 성별, 비밀번호, 질문, 답
    @Id @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_GEN")
    @SequenceGenerator(sequenceName = "USER_USERNO_SEQ", name = "USER_GEN", allocationSize = 1)
    private Long userId;

    @Column
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String phoneNum;

    @Column(nullable = false)
    private String birthDate;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String question;

    @Column(nullable = false)
    private String answer;

    @Column(unique = true)
    private String businessNum;

    @Column
    private String hptName;

    @Column(unique = true)
    private String hptAddress;

    @Column(unique = true)
    private String hptPhoneNum;

    public User(UserRequest requestDto, String password) {
        this.userRole = UserRole.USER;
        this.name = requestDto.getName();
        this.email = requestDto.getEmail();
        this.phoneNum = requestDto.getPhoneNum();
        this.password = password;
        this.birthDate = requestDto.getBirthDate();
        this.gender = requestDto.getGender();
        this.question = requestDto.getQuestion();
        this.answer = requestDto.getAnswer();
        this.businessNum = "-";
        this.hptName = "-";
        this.hptAddress = "-";
        this.phoneNum = "-";
    }

    @Builder
    private User(String password, UserRole userRole, String email) {
        this.password = password;
        this.userRole = userRole;
        this.email = email;
    }

    @Builder
    private User(UserRequest requestDto) {
        this.password = requestDto.getPassword();
        this.userRole = requestDto.getUserRole();
        this.email = requestDto.getEmail();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 계정의 권한 목록을 리턴
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(userRole.getValue()));
        return roles;
    }

    @Override
    public String getPassword() {
        return this.password; // 계정의 비밀번호 리턴
    }

    @Override
    public String getUsername() {
        return this.email; // 계정의 고유한 값 리턴
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // 계정의 만료 여부 리턴
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // 계정의 잠김 여부 리턴
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  // 비밀번호 만료 여부 리턴
    }

    @Override
    public boolean isEnabled() {
        return true; // 계정의 활성화 여부 리턴
    }

    //실험용,, 삭제 플리즈
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}