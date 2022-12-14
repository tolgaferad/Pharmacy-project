package com.pharmacy.demo.models.repository;
import com.pharmacy.demo.models.pojo.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository  
public interface PharmacyRepository extends JpaRepository<Pharmacy,Integer> {
    Pharmacy findById(Number id);
}
