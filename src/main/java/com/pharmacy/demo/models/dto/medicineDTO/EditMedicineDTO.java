package com.pharmacy.demo.models.dto.medicineDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EditMedicineDTO {
    private String name;
    private String barcode;
    private String strength;
    private String manufacturer;
    private String details;
    private Double price;
}
