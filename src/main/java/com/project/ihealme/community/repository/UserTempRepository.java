package com.project.ihealme.community.repository;

import com.project.ihealme.community.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTempRepository extends JpaRepository<User, Long> {

    User findByUserEmail(String userEmail);
}
