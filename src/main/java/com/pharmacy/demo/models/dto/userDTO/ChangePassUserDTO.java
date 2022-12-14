package com.pharmacy.demo.models.dto.userDTO;

import com.pharmacy.demo.utils.ValidPassword;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@Component
public class ChangePassUserDTO {
    @NotBlank(message = "Current password cannot be empty")
    private String currentPassword;
    @ValidPassword
    @NotBlank(message = "Password cannot be empty")
    private String password;
    @ValidPassword
    @NotBlank(message = "Confirm password cannot be empty")
    private String confirmPassword;
}
