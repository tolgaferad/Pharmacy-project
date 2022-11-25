package com.pharmacy.demo.controllers;

import com.pharmacy.demo.models.dto.userDTO.UserLoginDTO;
import com.pharmacy.demo.models.dto.userDTO.UserWithoutPasswordDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
public class UserController {
    @PostMapping("/users")
    public UserWithoutPasswordDTO login(@Valid @RequestBody UserLoginDTO userLoginDTO,
                                        HttpSession session) {

        return new UserWithoutPasswordDTO();
    }
}
