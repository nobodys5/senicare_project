package com.korit.sinicare.service;

import org.springframework.http.ResponseEntity;

import com.korit.sinicare.dto.request.nurse.PatchNurseRequestDto;
import com.korit.sinicare.dto.response.ResponseDto;
import com.korit.sinicare.dto.response.nurse.GetChargedCustomerResponseDto;
import com.korit.sinicare.dto.response.nurse.GetNurseListResponseDto;
import com.korit.sinicare.dto.response.nurse.GetNurseResponseDto;
import com.korit.sinicare.dto.response.nurse.GetSigninResponseDto;

public interface NurseService {
    
    ResponseEntity<? super GetNurseListResponseDto> getNUrseList(); 
    ResponseEntity<? super GetNurseResponseDto> getNurse(String userid);
    ResponseEntity<? super GetSigninResponseDto> getSignIn(String userId);
    ResponseEntity<ResponseDto> patchNurse(PatchNurseRequestDto dto, String userId);
    ResponseEntity<? super GetChargedCustomerResponseDto> getChargedCustomer(String nurseId);
}
