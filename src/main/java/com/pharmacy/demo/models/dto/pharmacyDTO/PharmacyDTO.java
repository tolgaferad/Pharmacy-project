package com.pharmacy.demo.models.dto.pharmacyDTO;

import javax.validation.constraints.NotBlank;

public class PharmacyDTO {
    @NotBlank(message = "Name cannot be empty")
    private String name;
    @NotBlank(message="Address cannot be empty")
    private String address;
}
