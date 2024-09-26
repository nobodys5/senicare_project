package com.korit.sinicare.service.implement;

import java.util.List;
import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.korit.sinicare.dto.customer.PatchCustomerRequestDto;
import com.korit.sinicare.dto.customer.PostCareRecordRequestDto;
import com.korit.sinicare.dto.customer.PostCustomerRequestDto;
import com.korit.sinicare.dto.response.ResponseDto;
import com.korit.sinicare.dto.response.customer.GetCareRecordListResponseDto;
import com.korit.sinicare.dto.response.customer.GetCustomerListResponseDto;
import com.korit.sinicare.dto.response.customer.GetCustomerResponseDto;
import com.korit.sinicare.entity.CareRecordEntity;
import com.korit.sinicare.entity.CustomerEntity;
import com.korit.sinicare.entity.ToolEntity;
import com.korit.sinicare.repository.CareRecordRepository;
import com.korit.sinicare.repository.CustomerRepository;
import com.korit.sinicare.repository.NurseRepository;
import com.korit.sinicare.repository.ToolRepository;
import com.korit.sinicare.repository.resultSet.GetCustomerResultSet;
import com.korit.sinicare.repository.resultSet.GetCustomersResultSet;
import com.korit.sinicare.service.CustomerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServieImplement implements CustomerService {

    private final NurseRepository nurseRepository;
    private final CustomerRepository customerRepository;
    private final CareRecordRepository careRecordRepository;
    private final ToolRepository toolRepository;

    @Override
    public ResponseEntity<ResponseDto> postCustomer(PostCustomerRequestDto dto) {
        
         try {

            String charger = dto.getCharger();
            boolean isExistedNurse = nurseRepository.existsByUserId(charger);
            if (!isExistedNurse) return ResponseDto.noExistUserId();

            CustomerEntity customerEntity = new CustomerEntity(dto);
            customerRepository.save(customerEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetCustomerListResponseDto> getCustomerList() {

        List<GetCustomersResultSet> resultSets = new ArrayList<>();


        try {
            
            resultSets = customerRepository.getCustomers();


        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
            return GetCustomerListResponseDto.success(resultSets);
    }

    @Override
    public ResponseEntity<? super GetCustomerResponseDto> getCustomer(Integer customerNumber) {
        
        GetCustomerResultSet resultSet = null;

        try {
            resultSet = customerRepository.getCustomer(customerNumber);
            if (resultSet == null) return ResponseDto.noExistCustomer();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetCustomerResponseDto.success(resultSet);
    }

    @Override
    public ResponseEntity<ResponseDto> patchCustomer(PatchCustomerRequestDto dto, Integer customerNumber, String userId) {
        
        try {
            CustomerEntity customerEntity = customerRepository.findByCustomerNumber(customerNumber);
            if (customerEntity == null) return ResponseDto.noExistCustomer();

            String charger = customerEntity.getCharger();
            boolean isCharger = charger.equals(userId);
            if (!isCharger) return ResponseDto.noPermission();

            customerEntity.patch(dto);
            customerRepository.save(customerEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> deleteCustomer(Integer customerNumber, String userId) {

        try {
            
            // customerentity repository에 있는 커스텀넘버를 찾아서 담는다.
            CustomerEntity customerEntity = customerRepository.findByCustomerNumber(customerNumber);
            // 만약 entity에 값이없으면 커스터머메시지오류를 띄운다.
            if (customerEntity == null) return ResponseDto.noExistCustomer();

            String charger = customerEntity.getCharger();
            boolean isCharger = charger.equals(userId);
            if (!isCharger) return ResponseDto.noPermission();

            careRecordRepository.deleteByCustomerNumber(customerNumber);
            customerRepository.delete(customerEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> postCareRecord(PostCareRecordRequestDto dto, Integer customerNumber,
            String userId) {

            try {

                ToolEntity toolEntity = null;
                String usedToolName = null;

                Integer usedCount = dto.getCount();
                Integer usedToolNumber = dto.getUsedToolNumber();

                if (usedToolNumber != null) {

                    toolEntity = toolRepository.findByToolNumber(usedToolNumber);
                    if (toolEntity == null) return ResponseDto.noExistTool();
                    Integer count = toolEntity.getCount();
                    if (usedCount > count) return ResponseDto.toolInsufficient();

                    usedToolName = toolEntity.getName();
                }

                CareRecordEntity careRecordEntity = new CareRecordEntity(dto, usedToolName, userId, customerNumber);
                careRecordRepository.save(careRecordEntity);

                if (usedToolNumber != null) {
                    toolEntity.decreaseCount(usedCount);
                    toolRepository.save(toolEntity);
                }

            } catch (Exception exception) {
                exception.printStackTrace();
                return ResponseDto.databaseError();
            }
            return ResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetCareRecordListResponseDto> getCareRecordList(Integer customerNumber) {

        List<CareRecordEntity> careRecordEntities = new ArrayList<>();

        try {
            careRecordEntities = careRecordRepository.findByCustomerNumberOrderByRecordNumberDesc(customerNumber);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetCareRecordListResponseDto.success(careRecordEntities);
    }
}
