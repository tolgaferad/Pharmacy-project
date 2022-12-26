package com.pharmacy.demo.models.pojo;

import com.pharmacy.demo.models.dto.medicineDTO.AddMedicineDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "medicines")
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String barcode;
    private String strength;
    private String manufacturer;
    private String details;
    private double price;
    private Timestamp expiryDate;
    @ManyToOne
    @JoinColumn(name = "shelf_id")
    private Shelf shelf;
    @ManyToOne
    @JoinColumn(name = "pharmacy_id")
    private Pharmacy pharmacy;
    @ManyToOne
    @JoinColumn(name = "sale_id")
    private Sale sale;

    public Medicine(AddMedicineDTO addMedicineDTO) {
        this.name = addMedicineDTO.getName();
        this.strength = addMedicineDTO.getStrength();
        this.manufacturer = addMedicineDTO.getManufacturer();
        this.details = addMedicineDTO.getDetails();
        this.price = addMedicineDTO.getPrice();
        this.expiryDate = addMedicineDTO.getExpiryDate();
    }
}
