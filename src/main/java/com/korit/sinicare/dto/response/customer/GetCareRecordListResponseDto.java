package com.korit.sinicare.dto.response.customer;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.korit.sinicare.common.utill.object.CareRecord;
import com.korit.sinicare.dto.response.ResponseCode;
import com.korit.sinicare.dto.response.ResponseDto;
import com.korit.sinicare.dto.response.ResponseMessage;
import com.korit.sinicare.entity.CareRecordEntity;

import lombok.Getter;

@Getter
public class GetCareRecordListResponseDto extends ResponseDto{
    
    private List<CareRecord> careRecords;

    private GetCareRecordListResponseDto(List<CareRecordEntity> careRecordEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.careRecords = CareRecord.getList(careRecordEntities);
    }

    public static ResponseEntity<GetCareRecordListResponseDto> success(List<CareRecordEntity> careRecordEntities) {
        GetCareRecordListResponseDto responseBody = new GetCareRecordListResponseDto(careRecordEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

}
