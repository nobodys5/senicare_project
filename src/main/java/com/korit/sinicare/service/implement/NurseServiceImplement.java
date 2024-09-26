package com.korit.sinicare.service.implement;

import java.util.List;
import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.korit.sinicare.dto.response.ResponseDto;
import com.korit.sinicare.dto.response.nurse.GetNurseListResponseDto;
import com.korit.sinicare.dto.response.nurse.GetNurseResponseDto;
import com.korit.sinicare.dto.response.nurse.GetSigninResponseDto;
import com.korit.sinicare.entity.NurseEntity;
import com.korit.sinicare.repository.NurseRepository;
import com.korit.sinicare.service.NurseService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NurseServiceImplement implements NurseService{

    private final NurseRepository nurseRepository;

    @Override
    public ResponseEntity<? super GetSigninResponseDto> getSignIn(String userId) {

        NurseEntity nurseEntity = null;

        try {

            nurseEntity = nurseRepository.findByUserId(userId);
            if (nurseEntity == null) return ResponseDto.noExistUserId();
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetSigninResponseDto.success(nurseEntity);
    }

    @Override
    public ResponseEntity<? super GetNurseListResponseDto> getNUrseList() {

        List<NurseEntity> nurseEntities = new ArrayList<>();

        try {
            
            nurseEntities = nurseRepository.findAll();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetNurseListResponseDto.success(nurseEntities);
    }

    // java는 반환타입이 다를때 로직이 같은 메서드라도 다른 메서드를 써줘야한다.
    @Override
    public ResponseEntity<? super GetNurseResponseDto> getNurse(String userId) {
        NurseEntity nurseEntity = null;

        try {

            nurseEntity = nurseRepository.findByUserId(userId);
            if (nurseEntity == null) return ResponseDto.noExistUserId();
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetNurseResponseDto.success(nurseEntity);
    }
    
}
