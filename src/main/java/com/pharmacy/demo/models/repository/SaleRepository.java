package com.pharmacy.demo.models.repository;

import com.pharmacy.demo.models.pojo.Sale;
import com.pharmacy.demo.models.pojo.Shelf;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Integer> {
    Sale findById(int number);
}
