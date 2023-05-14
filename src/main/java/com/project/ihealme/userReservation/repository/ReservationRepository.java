package com.project.ihealme.userReservation.repository;

import com.project.ihealme.community.domain.Post;
import com.project.ihealme.userReservation.domain.UserReservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<UserReservation, Long> {

    UserReservation findByEmailAndCurrentStatus(String userEmail, String currentStatus);
    UserReservation findByResNo(Long resNo);

    @Query(value = "SELECT DISTINCT u FROM UserReservation u ", countQuery = "select count(u) from UserReservation u")
    Page<UserReservation> findAllByPage(Pageable pageable);

    /**
     * 검색
     */
    // 병원명
    @Query(value = "SELECT DISTINCT u FROM UserReservation u "
            + "WHERE u.name like %:name%",
            countQuery = "select count(u) from UserReservation u")
    Page<UserReservation> findByNameContaining(@Param("name") String name, Pageable pageable);

    // 진료상태
    @Query(value = "SELECT DISTINCT u FROM UserReservation u "
            + "WHERE u.list like %:list%",
            countQuery = "select count(u) from UserReservation u")
    Page<UserReservation> findByListContaining(@Param("list") String list, Pageable pageable);

    // 상태
    @Query(value = "SELECT DISTINCT u FROM UserReservation u "
            + "WHERE u.currentStatus like %:currentStatus%",
            countQuery = "select count(u) from UserReservation u")
    Page<UserReservation> findByCurrentStatusContaining(@Param("currentStatus") String currentStatus, Pageable pageable);
}
