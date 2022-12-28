package com.pharmacy.demo.models.dto.shelfDTO;

import com.pharmacy.demo.models.dto.medicineDTO.ResponseMedicineDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
public class ResponseDeleteShelfDTO {
    private String name;
    private String pharmacyName;
    private int capacity;
}
