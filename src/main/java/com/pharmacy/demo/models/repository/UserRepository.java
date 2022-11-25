package com.pharmacy.demo.models.repository;

import com.pharmacy.demo.models.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUsername(String username);
    User findById(Number id);
}
