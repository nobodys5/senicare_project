package com.korit.sinicare.repository.resultSet;

// 고객 관리를 위해 사용되는 인터페이스
public interface GetCustomerResultSet {
    Integer getCustomerNumber();
    String getProfileImage();
    String getName();
    String getBirth();
    String getChargerName();
    String getChargerId();
    String getAddress();
}
