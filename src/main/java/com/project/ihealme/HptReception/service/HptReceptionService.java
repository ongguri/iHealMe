package com.project.ihealme.HptReception.service;

import com.project.ihealme.HptReception.domain.HptReception;
import com.project.ihealme.HptReception.dto.HptReceptionDto;
import com.project.ihealme.HptReception.dto.request.HptRecPageRequestDTO;
import com.project.ihealme.HptReception.dto.response.HptRecPageResponseDTO;
import com.project.ihealme.HptReception.repository.HptReceptionRepository;
import com.project.ihealme.userReservation.domain.UserReservation;
import com.project.ihealme.userReservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HptReceptionService {
    private final HptReceptionRepository hptReceptionRepository;

    @Transactional
    public HptReceptionDto getHptReception(Long recId) {
        return hptReceptionRepository.findById(recId)
                .map(HptReceptionDto::from)
                .orElseThrow(() -> new EntityNotFoundException("접수 정보가 없습니다."));
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
    public void updateCurrentStatus(Long recNo, String newStatus, LocalDateTime updateRegDate) {
        HptReception hptReception = hptReceptionRepository.getReferenceById(recNo);
        UserReservation userReservation = hptReception.getUserReservation();

        userReservation.setCurrentStatus(newStatus);

        hptReceptionRepository.save(hptReception);
    }

    public HptRecPageResponseDTO getUserResList(HptRecPageRequestDTO hptRecPageRequestDTO) {
        Page<HptReception> result = null;

        String type = hptRecPageRequestDTO.getType();
        Pageable pageable = hptRecPageRequestDTO.getPageable(Sort.by("recNo").descending());

        if (type == null || type.equals("")) {
            result = hptReceptionRepository.findAllByPage(pageable);
        } else {
            String keyword = hptRecPageRequestDTO.getKeyword();

            switch (type) {
                case "tx":
                    result = hptReceptionRepository.findByUserReservation_TxListContaining(keyword, pageable);
                    break;
                case "st":
                    result = hptReceptionRepository.findByUserReservation_CurrentStatusContaining(keyword, pageable);
                    break;
            }
        }

        return new HptRecPageResponseDTO(result);
    }

}
