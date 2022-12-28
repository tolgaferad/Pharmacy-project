package com.pharmacy.demo.models.pojo;

import com.pharmacy.demo.models.dto.shelfDTO.AddShelfDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="shelfs")
public class Shelf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(mappedBy = "shelf")
    private List<Medicine> medicines;
    @ManyToOne
    @JoinColumn(name="pharmacy_id")
    private Pharmacy pharmacy;
    private int capacity;

    public Shelf(AddShelfDTO addShelfDTO){
        this.name=addShelfDTO.getName();
        this.capacity=addShelfDTO.getCapacity();
    }
}
