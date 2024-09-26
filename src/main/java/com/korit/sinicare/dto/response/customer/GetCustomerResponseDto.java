package com.korit.sinicare.dto.response.customer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.korit.sinicare.dto.response.ResponseCode;
import com.korit.sinicare.dto.response.ResponseDto;
import com.korit.sinicare.dto.response.ResponseMessage;
import com.korit.sinicare.repository.resultSet.GetCustomerResultSet;

import lombok.Getter;

@Getter
public class GetCustomerResponseDto extends ResponseDto {
    
    private Integer customerNumber;
    private String profileImage;
    private String name;
    private String birth;
    private String chargerName;
    private String chargerId;
    private String address;

    // 
    private GetCustomerResponseDto (GetCustomerResultSet resultSet) {
        // 부모클래스인 responsedto에서 성공코드와 메세지를 받기위한 코드
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.customerNumber = resultSet.getCustomerNumber();
        this.profileImage = resultSet.getProfileImage();
        this.name = resultSet.getName();
        this.birth = resultSet.getBirth();
        this.chargerName = resultSet.getChargerName();
        this.chargerId = resultSet.getChargerId();
        this.address = resultSet.getAddress();
    }

    
    public static ResponseEntity<GetCustomerResponseDto> success(GetCustomerResultSet resultSet) {
        GetCustomerResponseDto responseBody = new GetCustomerResponseDto(resultSet);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
