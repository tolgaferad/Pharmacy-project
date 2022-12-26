package com.pharmacy.demo.models.repository;

import com.pharmacy.demo.models.pojo.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Integer> {
    List<Medicine> getMedicinesByShelfId(int id);

    List<Medicine> getMedicinesByPharmacyId(int id);
}
