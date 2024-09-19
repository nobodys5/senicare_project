package com.korit.sinicare.service.implement;

import org.springframework.http.ResponseEntity;

import com.korit.sinicare.dto.response.nurse.GetSigninResponseDto;

public interface NurseService {
    
    ResponseEntity<? super GetSigninResponseDto> getSignIn(String userId);

}
