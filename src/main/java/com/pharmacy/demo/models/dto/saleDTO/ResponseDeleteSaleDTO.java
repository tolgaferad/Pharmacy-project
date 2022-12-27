package com.pharmacy.demo.models.dto.saleDTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@Getter
@Setter
public class ResponseDeleteSaleDTO {
    private String name;
    private int price;
    private Timestamp createTime;
    private String pharmacyName;
}
