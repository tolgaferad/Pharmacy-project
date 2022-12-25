package com.pharmacy.demo.models.repository;

import com.pharmacy.demo.models.pojo.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine,Integer> {

}
