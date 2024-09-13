package com.korit.sinicare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.korit.sinicare.entity.NurseEntity;
import java.util.List;


@Repository
public interface NurseRepository extends JpaRepository<NurseEntity, String> {
    
    boolean existsByUserId(String userId);
    boolean existsByTelNumber(String telNumber);
    
    NurseEntity findByUserId(String userId);
    NurseEntity findBySnsIdAndJoinPath(String sns, String joinPath);

}
