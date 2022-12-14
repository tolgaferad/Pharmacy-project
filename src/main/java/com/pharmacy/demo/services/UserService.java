package com.pharmacy.demo.services;

import com.pharmacy.demo.exceptions.BadRequestException;
import com.pharmacy.demo.exceptions.NotFoundException;
import com.pharmacy.demo.models.Role;
import com.pharmacy.demo.models.dto.userDTO.*;
import com.pharmacy.demo.models.pojo.User;
import com.pharmacy.demo.models.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User login(UserLoginDTO userLoginDTO) {
        //TODO
        return new User();
    }
    public User getUserById(int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new NotFoundException("User not found");
        }
    }

    public User addUser(UserRegisterDTO userDTO) {
        userDTO.setCreateTime(new Timestamp(System.currentTimeMillis()));
        if (userRepository.findByUsername(userDTO.getUsername()) != null) {
            throw new BadRequestException("Username already exists");
        }
        if (userRepository.findByEmail(userDTO.getEmail()) != null) {
            throw new BadRequestException("Email already exists");
        }
        if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
            throw new BadRequestException("Confirm password doesn't match");
        }
        if(userDTO.getRole()!= Role.ADMIN || userDTO.getRole()!=Role.USER){
            throw new BadRequestException("The user role should be either ADMIN or USER");
        }
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        userDTO.setPassword(encoder.encode(userDTO.getPassword()));
        User user = new User(userDTO);
        user = userRepository.save(user);
        return user;
    }

    public User editUser(UpdateRequestUserDTO userDTO, int userId) {
        return  new User();
    }

    public User deleteUser(int userId) {
        return new User();
    }

    public User logoutUser(int userId) {
        return new User();
    }

    public User changePassword(int userId, ChangePassUserDTO changePasswordDTO) {
        return new User();
    }
}
