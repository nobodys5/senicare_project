package com.korit.sinicare.common.utill.object;

import java.util.List;

import com.korit.sinicare.repository.resultSet.GetCustomersResultSet;

import java.util.ArrayList;

import lombok.Getter;

@Getter
public class Customer {
    private Integer customerNumber;
    private String name;
    private String birth;
    private String location;
    private String chargerName;
    private String chargerId;

    private Customer(GetCustomersResultSet resultSet) {
        this.customerNumber = resultSet.getCustomerNumber();
        this.name = resultSet.getName();
        this.birth = resultSet.getBirth();
        this.location = resultSet.getLocation();
        this.chargerName = resultSet.getChargerName();
        this.chargerId = resultSet.getChargerId();

    }

    public static List<Customer> getList(List<GetCustomersResultSet> resultSets) {

        // 고객관리 리스트를 담기위해 만든 코드
        List<Customer> customers = new ArrayList<>();

        // foreach 반복문을 통해 resultsets에 값들을 하나씩 뽑아서 customer에 담아서 추가한것
        for (GetCustomersResultSet resultSet : resultSets) {
            Customer customer = new Customer(resultSet);
            customers.add(customer);
        }

        // 마지막으로 customer를 반환한다
        return customers;
    }
}
