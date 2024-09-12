package com.korit.sinicare.service.implement;

import org.springframework.http.ResponseEntity;

import com.korit.sinicare.dto.request.auth.IdCheckRequestDto;
import com.korit.sinicare.dto.request.auth.SignUpRequestDto;
import com.korit.sinicare.dto.request.auth.TelAuthCheckRequestDto;
import com.korit.sinicare.dto.request.auth.TelAuthRequestDto;
import com.korit.sinicare.dto.response.ResponseDto;

public interface AuthService {
    
    ResponseEntity<ResponseDto> idCheck(IdCheckRequestDto dto);
    ResponseEntity<ResponseDto> telAuth(TelAuthRequestDto dto);
    ResponseEntity<ResponseDto> telAuthCheck(TelAuthCheckRequestDto dto);
    ResponseEntity<ResponseDto> SignUp(SignUpRequestDto dto);

}
