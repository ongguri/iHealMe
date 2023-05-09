package com.project.ihealme.HptReception.service;

import com.project.ihealme.HptReception.domain.HptReception;
import com.project.ihealme.HptReception.repository.HptReceptionRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class HptReceptionService {
    private final HptReceptionRepository hptReceptionRepository;

    public HptReceptionService(HptReceptionRepository hptReceptionRepository) {
        this.hptReceptionRepository = hptReceptionRepository;
    }

    @Transactional
    public int getRtCount() {
        return hptReceptionRepository.findRtCount();
    }

    @Transactional
    public List<HptReception> getHptReceptionList() {
        return hptReceptionRepository.findAll(Sort.by(Sort.Direction.DESC, "resNo"));
    }

    @Transactional
    public void increaseRtCount() {
        hptReceptionRepository.increaseRtCount();
    }
    @Transactional
    public void decreaseRtCount() {
        hptReceptionRepository.decreaseRtCount();
    }

    @Transactional
    public void updateCurrentStatus(int resNo, String newStatus, LocalDateTime rDate) {
        // 해당 접수 번호(resNo)에 대한 HptReception 객체 가져오기
        HptReception hptReception = hptReceptionRepository.findByResNo(resNo);

        // 새로운 상태(newStatus)로 변경하기
        hptReception.setCurrentStatus(newStatus);

        // 접수 링크를 누른 시점의 sysdate 로 변경하기
        hptReception.setRDate(rDate);

        // 변경된 상태를 데이터베이스에 업데이트하기
        hptReceptionRepository.save(hptReception);
    }


}
