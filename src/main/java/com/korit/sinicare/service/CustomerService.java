package com.korit.sinicare.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.korit.sinicare.dto.customer.PatchCustomerRequestDto;
import com.korit.sinicare.dto.customer.PostCareRecordRequestDto;
import com.korit.sinicare.dto.customer.PostCustomerRequestDto;
import com.korit.sinicare.dto.response.ResponseDto;
import com.korit.sinicare.dto.response.customer.GetCareRecordListResponseDto;
import com.korit.sinicare.dto.response.customer.GetCustomerListResponseDto;
import com.korit.sinicare.dto.response.customer.GetCustomerResponseDto;


@Service
public interface CustomerService {

    ResponseEntity<ResponseDto> postCustomer(PostCustomerRequestDto requestBody);
    ResponseEntity<? super GetCustomerListResponseDto> getCustomerList();
    ResponseEntity<? super GetCustomerResponseDto> getCustomer(Integer customerNumber);
    ResponseEntity<ResponseDto> patchCustomer(PatchCustomerRequestDto dto, Integer customerNumber, String userId);
    ResponseEntity<ResponseDto> deleteCustomer(Integer customerNumber, String userId);
    ResponseEntity<ResponseDto> postCareRecord(PostCareRecordRequestDto dto, Integer customerNumber, String userId);
    ResponseEntity<? super GetCareRecordListResponseDto> getCareRecordList(Integer customerNumber);
}
