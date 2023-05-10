package com.project.ihealme.HptReception.repository;

import com.project.ihealme.HptReception.domain.HptReception;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HptReceptionRepository extends JpaRepository<HptReception, Long> {

    HptReception findByResNo(int resNo);

    @Query("SELECT rtCount FROM HptReception")
    Integer findRtCount();

    @Modifying
    @Query("UPDATE HptReception SET rtCount = rtCount + 1")
    void increaseRtCount();

    @Modifying
    @Query("UPDATE HptReception SET rtCount = rtCount - 1")
    void decreaseRtCount();

}
