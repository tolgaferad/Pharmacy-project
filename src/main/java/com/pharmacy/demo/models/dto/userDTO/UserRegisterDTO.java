package com.pharmacy.demo.models.dto.userDTO;


import com.pharmacy.demo.models.Role;
import com.pharmacy.demo.utils.ValidPassword;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class UserRegisterDTO {
    @NotBlank(message = "First Name is mandatory")
    private String name;
    @NotBlank(message = "Username is mandatory")
    private String username;
    @ValidPassword
    @NotBlank(message = "Password is mandatory")
    private String password;
    @ValidPassword
    @NotBlank(message = "Confirm password is mandatory")
    private String confirmPassword;
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Invalid email format")
    private String email;
    private Role role;
    private Timestamp createTime;


}

