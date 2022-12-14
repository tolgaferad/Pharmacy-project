package com.pharmacy.demo.models.dto.pharmacyDTO;

import javax.validation.constraints.NotBlank;

public class CreatePharmacyDTO {
    @NotBlank(message = "Name cannot be empty")
    private String name;
}
