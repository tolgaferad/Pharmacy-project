package com.pharmacy.demo.models.dto.userDTO;

import javax.validation.constraints.NotBlank;

public class UserLoginDTO {
    @NotBlank(message = "Username cannot be empty")
    public String username;
    @NotBlank(message = "Password cannot be empty")
    public String password;
}
