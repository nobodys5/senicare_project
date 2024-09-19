package com.korit.sinicare.dto.response.nurse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.korit.sinicare.dto.response.ResponseCode;
import com.korit.sinicare.dto.response.ResponseDto;
import com.korit.sinicare.dto.response.ResponseMessage;
import com.korit.sinicare.entity.NurseEntity;

import lombok.Getter;

@Getter
public class GetSigninResponseDto extends ResponseDto {
    
    private String userId;
    private String name;
    private String telNumber;

    public GetSigninResponseDto(NurseEntity nurseEntity) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.userId = nurseEntity.getUserId();
        this.name = nurseEntity.getName();
        this.telNumber = nurseEntity.getTelNumber();
    }

    public static ResponseEntity<GetSigninResponseDto> success(NurseEntity nurseEntity) {
        GetSigninResponseDto responseBody = new GetSigninResponseDto(nurseEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

}
