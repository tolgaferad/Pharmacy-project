package com.pharmacy.demo.services;

import com.pharmacy.demo.models.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService {
    @Autowired
    private UserRepository userRepository;

}
