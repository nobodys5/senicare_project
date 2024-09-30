package com.korit.sinicare.service.implement;

import java.util.List;
import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.korit.sinicare.dto.request.nurse.PatchNurseRequestDto;
import com.korit.sinicare.dto.response.ResponseDto;
import com.korit.sinicare.dto.response.nurse.GetChargedCustomerResponseDto;
import com.korit.sinicare.dto.response.nurse.GetNurseListResponseDto;
import com.korit.sinicare.dto.response.nurse.GetNurseResponseDto;
import com.korit.sinicare.dto.response.nurse.GetSigninResponseDto;
import com.korit.sinicare.entity.CustomerEntity;
import com.korit.sinicare.entity.NurseEntity;
import com.korit.sinicare.repository.CustomerRepository;
import com.korit.sinicare.repository.NurseRepository;
import com.korit.sinicare.service.NurseService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NurseServiceImplement implements NurseService{

    private final NurseRepository nurseRepository;
    private final CustomerRepository customerRepository;

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

    @Override
    public ResponseEntity<ResponseDto> patchNurse(PatchNurseRequestDto dto, String userId) {
        
        try {
            
            String name = dto.getName();

            // patch 작업을 수해할 시 값을바꿔야하기때문에 먼저 해당값이 있는지확인하기 위한 코드
            NurseEntity nurseEntity = nurseRepository.findByUserId(userId);
            // 해당하는 결과값이 null이면 오류메세지띄워주는 코드
            if (nurseEntity == null) return ResponseDto.noExistUserId();
            // 엔티티에 값을 넣어준다
            nurseEntity.setName(name);
            // 엔티티에 담은 값을 save로 저장시킨다
            nurseRepository.save(nurseEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
            return ResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetChargedCustomerResponseDto> getChargedCustomer(String nurseId) {
        

        List<CustomerEntity> customerEntities = new ArrayList<>();

        try {
            
            customerEntities = customerRepository.findByCharger(nurseId);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
            return GetChargedCustomerResponseDto.success(customerEntities);
    }
    
}
