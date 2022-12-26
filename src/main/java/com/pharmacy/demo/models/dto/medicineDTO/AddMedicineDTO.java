package com.pharmacy.demo.models.dto.medicineDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.sql.Timestamp;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddMedicineDTO {
    @NotBlank(message = "Name cannot be empty")
    private String name;
    @NotBlank(message = "Barcode cannot be empty")
    private String strength;
    private String manufacturer;
    private String details;
    @NotNull(message = "Price cannot be null")
    private double price;
    @NotNull(message = "Expiry date cannot be empty")
    private Timestamp expiryDate;
    private int quantity;
}
