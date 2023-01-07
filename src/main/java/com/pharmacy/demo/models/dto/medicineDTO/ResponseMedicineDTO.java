package com.pharmacy.demo.models.dto.medicineDTO;

import com.pharmacy.demo.models.pojo.Medicine;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@Component
public class ResponseMedicineDTO {
    private int id;
    private String name;
    private String barcode;
    private String strength;
    private String manufacturer;
    private String details;
    private double price;
    private Timestamp expiryDate;
    private String shelfName;
    private String pharmacyName;

    public ResponseMedicineDTO(Medicine medicine) {
        this.id=medicine.getId();
        this.name = medicine.getName();
        this.barcode = medicine.getBarcode();
        this.strength = medicine.getStrength();
        this.manufacturer = medicine.getManufacturer();
        this.details = medicine.getDetails();
        this.price = medicine.getPrice();
        this.expiryDate = medicine.getExpiryDate();
        this.pharmacyName = medicine.getPharmacy().getName();
    }
}
