package com.pharmacy.demo.models.dto.saleDTO;

import com.pharmacy.demo.models.dto.saleDetailsDTO.ResponseSaleDetailDTO;
import com.pharmacy.demo.models.pojo.SaleDetail;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

@Component
@Getter
@Setter
public class ResponseSaleDTO {
    private String name;
    private int price;
    private Timestamp createTime;
    private String pharmacyName;
    private List<ResponseSaleDetailDTO> saleDetails;
}
