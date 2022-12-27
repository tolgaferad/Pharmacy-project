package com.pharmacy.demo.models.dto.saleDetailsDTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@Getter
@Setter
public class ResponseSaleDetailDTO {
    private String name;
    private String strength;
    private String manufacturer;
    private String details;
    private double price;
}
