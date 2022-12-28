package com.pharmacy.demo.models.dto.medicineDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Positive;
import java.sql.Timestamp;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilterMedicineDTO {
    private String name;
    private String barcode;
    private String strength;
    private String manufacturer;
    @Positive(message = "Price must be positive")
    private Double price;
    private Timestamp expiryDate;
}
