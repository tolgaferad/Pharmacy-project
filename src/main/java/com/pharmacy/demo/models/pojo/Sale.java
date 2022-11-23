package com.pharmacy.demo.models.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="sales")
public class Sale {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    @OneToMany(mappedBy = "sale")
    private List<Medicine> medicines;

}
