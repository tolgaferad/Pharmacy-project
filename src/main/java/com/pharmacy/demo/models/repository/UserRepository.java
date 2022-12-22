package com.pharmacy.demo.models.repository;
import com.pharmacy.demo.models.pojo.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUsername(String username);
    User findById(Number id);
    User findByEmail(String email);
    User findByIdAndPharmacyId(int userId, int pharmacyId);
}
