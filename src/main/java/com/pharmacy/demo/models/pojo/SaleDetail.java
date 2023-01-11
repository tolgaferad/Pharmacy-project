package com.pharmacy.demo.models.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="sale_details")
public class SaleDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String strength;
    private String manufacturer;
    private String details;
    private double price;
    private int medicineId;
    @ManyToOne
    @JoinColumn(name = "sale_id")
    private Sale sale;
    public SaleDetail(Medicine medicine){
        this.name=medicine.getName();
        this.strength=medicine.getStrength();
        this.manufacturer=medicine.getManufacturer();
        this.details=medicine.getDetails();
        this.price=medicine.getPrice();
        this.medicineId=medicine.getId();
    }
}
