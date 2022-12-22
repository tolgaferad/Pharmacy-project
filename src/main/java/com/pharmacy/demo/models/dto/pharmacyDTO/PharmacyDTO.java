package com.pharmacy.demo.models.dto.pharmacyDTO;

import com.pharmacy.demo.models.pojo.Pharmacy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PharmacyDTO {
    @NotBlank(message = "Name cannot be empty")
    private String name;
    @NotBlank(message = "Address cannot be empty")
    private String address;

}
