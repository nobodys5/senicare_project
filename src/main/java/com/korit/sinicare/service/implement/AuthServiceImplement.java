package com.korit.sinicare.service.implement;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.korit.sinicare.common.utill.AuthNumberCreator;
import com.korit.sinicare.dto.request.auth.IdCheckRequestDto;
import com.korit.sinicare.dto.request.auth.SignUpRequestDto;
import com.korit.sinicare.dto.request.auth.TelAuthCheckRequestDto;
import com.korit.sinicare.dto.request.auth.TelAuthRequestDto;
import com.korit.sinicare.dto.response.ResponseDto;
import com.korit.sinicare.entity.TelAuthNumberEntity;
import com.korit.sinicare.provider.SmsProvider;
import com.korit.sinicare.repository.NurseRepository;
import com.korit.sinicare.repository.TelAuthNumberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {

    private final SmsProvider smsProvider;

    private final NurseRepository nurseRepository;
    private final TelAuthNumberRepository telAuthNumberRepository;

    @Override
    public ResponseEntity<ResponseDto> idCheck(IdCheckRequestDto dto) {

        String userID = dto.getUserId();

        try {
            
            boolean isExistedId = nurseRepository.existsByUserId(userID);
            if (isExistedId) return ResponseDto.duplicatedUserId();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
            
        }

        // return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("SU", "Success"));

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> telAuth(TelAuthRequestDto dto) {
        String telnumber = dto.getTelNumber();

        try {
            
            boolean isExistedTelNumber = nurseRepository.existsByTelNumber(telnumber);
            if (isExistedTelNumber) return ResponseDto.duplicatedTelNumber();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        String authNumber = AuthNumberCreator.number4();

        boolean issendSuccess = smsProvider.sendMessage(telnumber, authNumber);
        if (!issendSuccess) return ResponseDto.messageSendFail();

        try {
            
            TelAuthNumberEntity telAuthNumberEntity = new TelAuthNumberEntity(telnumber, authNumber);
            telAuthNumberRepository.save(telAuthNumberEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> telAuthCheck(TelAuthCheckRequestDto dto) {
        
        String telNumber = dto.getTelNumber();
        String authNumber = dto.getAuthNumber();

        try {
            
            boolean isMatched = telAuthNumberRepository.existsByTelNumberAndAuthNumber(telNumber, authNumber);
            if (!isMatched) return ResponseDto.telAuthFail();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> SignUp(SignUpRequestDto dto) {

        String signUpId = dto.getUserId();
        String signUppassword = dto.getPassword();

        return ResponseDto.success();
    }

    
    
}
