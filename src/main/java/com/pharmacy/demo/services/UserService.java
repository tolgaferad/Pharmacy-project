package com.pharmacy.demo.services;

import com.pharmacy.demo.exceptions.AuthenticationException;
import com.pharmacy.demo.exceptions.BadRequestException;
import com.pharmacy.demo.exceptions.NotFoundException;
import com.pharmacy.demo.models.dto.userDTO.*;
import com.pharmacy.demo.models.pojo.User;
import com.pharmacy.demo.models.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUserById(int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new NotFoundException("User not found");
        }
    }

    public User login(UserLoginDTO loginUserDto) {
        User user = userRepository.findByUsername(loginUserDto.getUsername());
        if (user == null) {
            throw new AuthenticationException("Wrong credentials");
        } else {
            PasswordEncoder encoder = new BCryptPasswordEncoder();
            if (encoder.matches(loginUserDto.getPassword(), user.getPassword())) {
                return user;
            } else {
                throw new AuthenticationException("Wrong credentials");
            }
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
        if(!List.of("ADMIN", "USER").contains(userDTO.getRole())){
            throw new BadRequestException("Role should be either ADMIN or USER");
        }
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        userDTO.setPassword(encoder.encode(userDTO.getPassword()));
        User user = new User(userDTO);
        user = userRepository.save(user);
        return user;
    }

    public User editUser(UpdateRequestUserDTO userDTO, int userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        if (userDTO.getUsername() != null) {
            String currentUsername = user.get().getUsername();
            if (userRepository.findByUsername(userDTO.getUsername()) != null && !userDTO.getUsername().equals(currentUsername)) {
                throw new BadRequestException("Username already exists");
            }
            user.get().setUsername(userDTO.getUsername());
        }
        if (userDTO.getName() != null) {
            user.get().setName(userDTO.getName());
        }
        if (userDTO.getEmail() != null) {
            String currentEmail = user.get().getEmail();
            if (userRepository.findByEmail(userDTO.getEmail()) != null && !userDTO.getEmail().equals(currentEmail)) {
                throw new BadRequestException("Email already exists");
            }
            user.get().setEmail(userDTO.getEmail());
        }
        return userRepository.save(user.get());
    }

    public UserWithoutPasswordDTO deleteUser(int userId) {
        Optional<User> optUser = userRepository.findById(userId);
        if (optUser.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        User user = optUser.get();
        //cannot map entity after it is deleted
        UserWithoutPasswordDTO responseUser = new UserWithoutPasswordDTO(user);
        userRepository.delete(user);
        return responseUser;
    }

    public User logoutUser(int userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        return user.get();
    }

    public User changePassword(int userId, ChangePassUserDTO changePasswordDTO) {
        Optional<User> optUser = userRepository.findById(userId);
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        if (optUser.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        if (!encoder.matches(changePasswordDTO.getCurrentPassword(), optUser.get().getPassword())) {
            throw new AuthenticationException("Invalid current password");
        }
        if (!changePasswordDTO.getPassword().equals(changePasswordDTO.getConfirmPassword())) {
            throw new AuthenticationException("Confirm password doesn't match");
        }
        if (encoder.matches(changePasswordDTO.getPassword(), optUser.get().getPassword())) {
            throw new AuthenticationException("Entered the same password as current one");
        } else {
            User user = optUser.get();
            user.setPassword(encoder.encode(changePasswordDTO.getPassword()));
            userRepository.save(user);
            return user;
        }
    }
}
