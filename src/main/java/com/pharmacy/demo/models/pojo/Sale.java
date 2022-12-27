package com.pharmacy.demo.models.pojo;

import com.pharmacy.demo.models.dto.saleDTO.AddSaleDTO;
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
    private boolean isConfirmed;
    @OneToMany(mappedBy = "sale")
    private List<SaleDetail> saleDetails;
    @ManyToOne
    @JoinColumn(name="pharmacy_id")
    private Pharmacy pharmacy;
    public Sale(AddSaleDTO addSaleDTO) {
         this.name=addSaleDTO.getName();
         this.createTime=addSaleDTO.getCreateTime();
    }
}
