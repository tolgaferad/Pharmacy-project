package com.pharmacy.demo.models.dto.userDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
@Getter
@Setter
@NoArgsConstructor
@Component
public class UserLoginDTO {
    @NotBlank(message = "Username cannot be empty")
    public String username;
    @NotBlank(message = "Password cannot be empty")
    public String password;
}
