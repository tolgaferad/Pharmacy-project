package com.pharmacy.demo.models.dto.shelfDTO;

import com.pharmacy.demo.models.dto.medicineDTO.ResponseMedicineDTO;
import com.pharmacy.demo.models.pojo.Medicine;
import com.pharmacy.demo.models.pojo.Pharmacy;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Component
@Getter
@Setter
public class ResponseShelfDTO {
    private int id;
    private String name;
    private List<ResponseMedicineDTO> medicines;
    private String pharmacyName;
    private int capacity;
}
