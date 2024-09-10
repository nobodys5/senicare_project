package com.korit.sinicare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.korit.sinicare.entity.CareRecordEntity;

@Repository
public interface CareRecordRepository extends JpaRepository<CareRecordEntity, Integer> {
    

}
