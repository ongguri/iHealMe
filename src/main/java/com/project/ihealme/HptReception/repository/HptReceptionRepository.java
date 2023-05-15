package com.project.ihealme.HptReception.repository;

import com.project.ihealme.HptReception.domain.HptReception;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HptReceptionRepository extends JpaRepository<HptReception, Long> {

    HptReception findByResNo(int resNo);

    @Query("SELECT rtCount FROM HptReception")
    Integer findRtCount();

    @Query(value = "SELECT DISTINCT h FROM HptReception h ", countQuery = "select count(h) from HptReception h")
    Page<HptReception> findAllByPage(Pageable pageable);

    @Modifying
    @Query("UPDATE HptReception SET rtCount = rtCount + 1")
    void increaseRtCount();

    @Modifying
    @Query("UPDATE HptReception SET rtCount = rtCount - 1")
    void decreaseRtCount();

    /**
     * 검색
     */
    // 진료상태
    @Query(value = "SELECT DISTINCT h FROM HptReception h "
            + "WHERE h.txList like %:txList%",
            countQuery = "select count(h) from HptReception h")
    Page<HptReception> findByTxListContaining(@Param("txList") String txList, Pageable pageable);

    // 상태
    @Query(value = "SELECT DISTINCT h FROM HptReception h "
            + "WHERE h.currentStatus like %:currentStatus%",
            countQuery = "select count(u) from UserReservation u")
    Page<HptReception> findByCurrentStatusContaining(@Param("currentStatus") String currentStatus, Pageable pageable);

}
