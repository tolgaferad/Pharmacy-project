package com.pharmacy.demo.models.dto.userDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;

@Getter
@Setter
@NoArgsConstructor
@Component
public class UpdateRequestUserDTO {
    private String name;
    private String username;
    @Email(message = "Invalid email format")
    private String email;
}
