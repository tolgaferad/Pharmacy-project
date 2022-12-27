package com.pharmacy.demo.utils;

import com.pharmacy.demo.exceptions.BadRequestException;
import com.pharmacy.demo.exceptions.NotFoundException;
import com.pharmacy.demo.models.pojo.User;
import com.pharmacy.demo.models.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Utils {
    @Autowired
    UserRepository userRepository;
    public User checkWhetherUserHasPharmacy(int userId){
        Optional<User> optUser = userRepository.findById(userId);
        if (optUser.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        if(optUser.get().getPharmacy()==null){
            throw new BadRequestException("User doesn't have pharmacy");
        }
        return optUser.get();
    }
}
