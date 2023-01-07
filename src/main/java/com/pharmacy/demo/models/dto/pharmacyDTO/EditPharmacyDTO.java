package com.pharmacy.demo.models.dto.pharmacyDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EditPharmacyDTO {
    private String name;
    private String address;
}
