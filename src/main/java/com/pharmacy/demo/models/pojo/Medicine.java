package com.pharmacy.demo.models.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="medicines")
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String barcode;
    private String strength;
    private String manufacturer;
    private String details;
    private double price;
    private Timestamp expiryDate;
    @ManyToOne
    @JoinColumn(name="shelf_id")
    private Shelf shelf;
    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;
    @ManyToOne
    @JoinColumn(name="pharmacy_id")
    private Pharmacy pharmacy;
    @ManyToOne
    @JoinColumn(name="sale_id")
    private Sale sale;
}
