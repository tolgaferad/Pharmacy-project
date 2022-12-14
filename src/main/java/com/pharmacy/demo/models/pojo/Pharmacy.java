package com.pharmacy.demo.models.pojo;

import com.pharmacy.demo.models.dto.pharmacyDTO.PharmacyDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="pharmacies")
public class Pharmacy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String address;
    @OneToMany(mappedBy = "pharmacy")
    private List<User> users;
    @OneToMany(mappedBy = "pharmacy")
    private List<Medicine> medicine;
    @OneToMany(mappedBy = "pharmacy")
    private List<Shelf> shelfs;
    @OneToMany(mappedBy = "pharmacy")
    private List<Sale> sales;

    public Pharmacy(PharmacyDTO pharmacyDTO){
        this.name=pharmacyDTO.getName();
        this.address=pharmacyDTO.getAddress();
    }
}
