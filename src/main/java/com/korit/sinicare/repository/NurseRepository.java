package com.korit.sinicare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.korit.sinicare.entity.NurseEntity;

@Repository
public interface NurseRepository extends JpaRepository<NurseEntity, String> {
    
    boolean existsByUserId(String userId);
    boolean existsByTelNumber(String telNumber);

}
