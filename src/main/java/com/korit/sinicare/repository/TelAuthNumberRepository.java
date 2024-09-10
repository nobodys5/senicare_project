package com.korit.sinicare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.korit.sinicare.entity.TelAuthNumberEntity;

@Repository
public interface TelAuthNumberRepository extends JpaRepository<TelAuthNumberEntity, String> {
    
}
