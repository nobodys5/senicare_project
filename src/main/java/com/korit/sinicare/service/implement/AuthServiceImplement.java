package com.korit.sinicare.service.implement;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.korit.sinicare.common.utill.AuthNumberCreator;
import com.korit.sinicare.dto.request.auth.IdCheckRequestDto;
import com.korit.sinicare.dto.request.auth.SignUpRequestDto;
import com.korit.sinicare.dto.request.auth.SigninRequestDto;
import com.korit.sinicare.dto.request.auth.TelAuthCheckRequestDto;
import com.korit.sinicare.dto.request.auth.TelAuthRequestDto;
import com.korit.sinicare.dto.response.ResponseDto;
import com.korit.sinicare.dto.response.auth.SignInResponseDto;
import com.korit.sinicare.entity.NurseEntity;
import com.korit.sinicare.entity.TelAuthNumberEntity;
import com.korit.sinicare.provider.JwtProvider;
import com.korit.sinicare.provider.SmsProvider;
import com.korit.sinicare.repository.NurseRepository;
import com.korit.sinicare.repository.TelAuthNumberRepository;
import com.korit.sinicare.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {

    private final SmsProvider smsProvider;

    private final JwtProvider jwtProvider;

    private final NurseRepository nurseRepository;
    private final TelAuthNumberRepository telAuthNumberRepository;
    

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

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

        String userId = dto.getUserId();
        String telNumber = dto.getTelNumber();
        String authNumber = dto.getAuthNumber();
        String password = dto.getPassword();

        try {
            
            boolean isExistedId = nurseRepository.existsById(userId);
            if (isExistedId) return ResponseDto.duplicatedUserId();

            boolean isExistedTelNumber = nurseRepository.existsByTelNumber(telNumber);
            if (isExistedTelNumber) return ResponseDto.duplicatedTelNumber();

            boolean isMatched = telAuthNumberRepository.existsByTelNumberAndAuthNumber(telNumber, authNumber);
            if (!isMatched) return ResponseDto.telAuthFail();

            String encodedPassword = passwordEncoder.encode(password);
            dto.setPassword(encodedPassword);

            NurseEntity nurseEntity = new NurseEntity(dto);
            nurseRepository.save(nurseEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<? super SignInResponseDto> signIn(SigninRequestDto dto) {

        String userId = dto.getUserId();
        String password = dto.getPassword();
        

        String accessToken = null;

        try {

            NurseEntity nurseEntity = nurseRepository.findByUserId(userId);
            if (nurseEntity == null) return ResponseDto.signInFail();

            String encodedPassword = nurseEntity.getPassword();
            // 평문의 비밀번호와 암호화된 비밀번호가 같으면 true 비교해주는 코드
            boolean isMatched = passwordEncoder.matches(password, encodedPassword);
            if (!isMatched) return ResponseDto.signInFail();

            accessToken = jwtProvider.create(userId);
            if (accessToken == null) return ResponseDto.tokenCreatefail();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return SignInResponseDto.success(accessToken);
    }

    
    
}
