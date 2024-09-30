package com.korit.sinicare.common.utill.object;

import java.util.List;
import java.util.ArrayList;

import com.korit.sinicare.entity.CustomerEntity;

import lombok.Getter;

@Getter
public class ChargedCustomer {
    private Integer customerNumber; 
    private String name;
    private String birth;
    private String location;

    // chargedcustomer 값을 저장할 생성자 생성
    private ChargedCustomer(CustomerEntity customerEntity) {
        this.customerNumber = customerEntity.getCustomerNumber();
        this.name = customerEntity.getName();
        this.birth = customerEntity.getBirth();
        this.location = customerEntity.getLocation();
    }

    // static을 사용하여 getList 메서드를 만들고 for each 반복문을 통해 getList에 값들을 담아와서 저장한다.
    public static List<ChargedCustomer> getList(List<CustomerEntity> customerEntities) {
        List<ChargedCustomer> customers = new ArrayList<>();

        for (CustomerEntity customerEntity: customerEntities) {

            ChargedCustomer customer = new ChargedCustomer(customerEntity);
            customers.add(customer);
        }

        return customers;
    }
}
