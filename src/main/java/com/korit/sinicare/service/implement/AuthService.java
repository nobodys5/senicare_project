package com.korit.sinicare.service.implement;

import org.springframework.http.ResponseEntity;

import com.korit.sinicare.dto.request.auth.IdCheckRequestDto;
import com.korit.sinicare.dto.request.auth.SignUpRequestDto;
import com.korit.sinicare.dto.request.auth.SigninRequestDto;
import com.korit.sinicare.dto.request.auth.TelAuthCheckRequestDto;
import com.korit.sinicare.dto.request.auth.TelAuthRequestDto;
import com.korit.sinicare.dto.response.ResponseDto;
import com.korit.sinicare.dto.response.auth.SignInResponseDto;


public interface AuthService {
    
    ResponseEntity<ResponseDto> idCheck(IdCheckRequestDto dto);
    ResponseEntity<ResponseDto> telAuth(TelAuthRequestDto dto);
    ResponseEntity<ResponseDto> telAuthCheck(TelAuthCheckRequestDto dto);
    ResponseEntity<ResponseDto> SignUp(SignUpRequestDto dto);
    // 클래스명 먼저 import받아야 와일드카드로 제너릭타입 지정 시 오류가안뜬다
    ResponseEntity<? super SignInResponseDto> signIn(SigninRequestDto dto);
    
}
