package com.korit.sinicare.dto.response.nurse;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.korit.sinicare.common.utill.object.ChargedCustomer;
import com.korit.sinicare.dto.response.ResponseCode;
import com.korit.sinicare.dto.response.ResponseDto;
import com.korit.sinicare.dto.response.ResponseMessage;
import com.korit.sinicare.entity.CustomerEntity;

import lombok.Getter;

@Getter
public class GetChargedCustomerResponseDto extends ResponseDto {

    private List<ChargedCustomer> customers;

    private GetChargedCustomerResponseDto(List<CustomerEntity> customerEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.customers = ChargedCustomer.getList(customerEntities);

    }

    public static ResponseEntity<GetChargedCustomerResponseDto> success(List<CustomerEntity> customerEntities) {
        GetChargedCustomerResponseDto response = new GetChargedCustomerResponseDto(customerEntities);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}