package com.pharmacy.demo.models.repository;

import com.pharmacy.demo.models.pojo.Shelf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShelfRepository extends JpaRepository<Shelf, Integer> {
    Shelf findById(int number);
}
