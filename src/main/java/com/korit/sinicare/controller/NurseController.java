package com.korit.sinicare.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.korit.sinicare.dto.response.nurse.GetSigninResponseDto;
import com.korit.sinicare.service.implement.NurseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/nurse")
@RequiredArgsConstructor
public class NurseController {

    private final NurseService nurseService;
    
    @GetMapping("/sign-in")
    public ResponseEntity<? super GetSigninResponseDto> getSignIn(
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super GetSigninResponseDto> response = nurseService.getSignIn(userId);
        return response;
    }
}
