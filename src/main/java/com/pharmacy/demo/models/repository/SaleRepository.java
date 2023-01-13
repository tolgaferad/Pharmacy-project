package com.pharmacy.demo.models.repository;

import com.pharmacy.demo.models.pojo.Medicine;
import com.pharmacy.demo.models.pojo.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Integer> {
    Sale findById(int number);
    List<Sale> getSalesByPharmacyId(int id);
}
