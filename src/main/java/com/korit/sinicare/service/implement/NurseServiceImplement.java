package com.korit.sinicare.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.korit.sinicare.dto.response.ResponseDto;
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
    
}
