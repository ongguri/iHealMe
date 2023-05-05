package com.project.ihealme.HptReception.repository;

import com.project.ihealme.HptReception.domain.HptReception;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HptReceptionRepository {
    HptReception findByResNo(int resNo);
    @Query("SELECT rtCount FROM HptReception")
    int findRtCount();

    @Modifying
    @Query("UPDATE HptReception SET rtCount = rtCount + 1")
    void increaseRtCount();
    @Modifying
    @Query("UPDATE HptReception SET rtCount = rtCount - 1")
    void decreaseRtCount();

    List<HptReception> findAll(Sort resNo);

    void save(HptReception hptReception);
}
