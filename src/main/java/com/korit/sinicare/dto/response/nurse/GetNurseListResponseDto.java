package com.korit.sinicare.dto.response.nurse;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.korit.sinicare.common.utill.object.Nurse;
import com.korit.sinicare.dto.response.ResponseCode;
import com.korit.sinicare.dto.response.ResponseDto;
import com.korit.sinicare.dto.response.ResponseMessage;
import com.korit.sinicare.entity.NurseEntity;

import lombok.Getter;

@Getter
public class GetNurseListResponseDto extends ResponseDto {
    private List<Nurse> nurses;

    //  NurseEntity 값들을 매개변수로 받고 꺼내서 사용하기위한 메서드
    private GetNurseListResponseDto(List<NurseEntity> nurseEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.nurses = Nurse.getList(nurseEntities);
    }

    public static ResponseEntity<GetNurseListResponseDto> success(List<NurseEntity> nurseEntities) {
        GetNurseListResponseDto responseBody = new GetNurseListResponseDto(nurseEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
