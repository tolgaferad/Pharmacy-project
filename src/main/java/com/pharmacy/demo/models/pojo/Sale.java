package com.pharmacy.demo.models.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="sales")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int price;
    private Timestamp createTime;
    @OneToMany(mappedBy = "sale")
    private List<Medicine> medicines;

}
