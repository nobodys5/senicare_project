package com.korit.sinicare.dto.response.nurse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.korit.sinicare.dto.response.ResponseCode;
import com.korit.sinicare.dto.response.ResponseDto;
import com.korit.sinicare.dto.response.ResponseMessage;
import com.korit.sinicare.entity.NurseEntity;

import lombok.Getter;

@Getter
public class GetNurseResponseDto extends ResponseDto  {
    private String userId;
    private String name;
    private String telNUmber;

    private GetNurseResponseDto(NurseEntity nurseEntity) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.userId = nurseEntity.getUserId();
        this.name = nurseEntity.getName();
        this.telNUmber = nurseEntity.getTelNumber();
    }

    public static ResponseEntity<GetNurseResponseDto> success(NurseEntity nurseEntity) {
        GetNurseResponseDto responseBody = new GetNurseResponseDto(nurseEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
