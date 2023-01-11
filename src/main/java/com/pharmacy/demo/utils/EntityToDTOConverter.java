package com.pharmacy.demo.utils;

import com.pharmacy.demo.models.dto.medicineDTO.ResponseMedicineDTO;
import com.pharmacy.demo.models.dto.pharmacyDTO.PharmacyDTO;
import com.pharmacy.demo.models.dto.saleDTO.ResponseDeleteSaleDTO;
import com.pharmacy.demo.models.dto.saleDTO.ResponseSaleDTO;
import com.pharmacy.demo.models.dto.saleDetailsDTO.ResponseSaleDetailDTO;
import com.pharmacy.demo.models.dto.shelfDTO.ResponseDeleteShelfDTO;
import com.pharmacy.demo.models.dto.shelfDTO.ResponseShelfDTO;
import com.pharmacy.demo.models.dto.userDTO.UserWithoutPasswordDTO;
import com.pharmacy.demo.models.pojo.*;

import org.modelmapper.ModelMapper;

public class EntityToDTOConverter {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static UserWithoutPasswordDTO convertToUserWithoutPass(User user) {
        return modelMapper.map(user, UserWithoutPasswordDTO.class);
    }

    public static PharmacyDTO convertToPharmacyDTO(Pharmacy pharmacy) {
        return modelMapper.map(pharmacy, PharmacyDTO.class);
    }

    public static ResponseMedicineDTO convertToResponsMedicineDTO(Medicine medicine) {
        return modelMapper.map(medicine, ResponseMedicineDTO.class);
    }

    public static ResponseSaleDTO convertToResponseSaleDTO(Sale sale){
        return modelMapper.map(sale,ResponseSaleDTO.class);
    }

    public static ResponseSaleDetailDTO convertToResponseSaleDetailDTO(SaleDetail saleDetail){
        return modelMapper.map(saleDetail,ResponseSaleDetailDTO.class);
    }

    public static ResponseDeleteSaleDTO convertToResponseDeleteSaleDTO(Sale sale){
        return modelMapper.map(sale,ResponseDeleteSaleDTO.class);
    }

    public static ResponseShelfDTO convertToResponseShelfDTO(Shelf shelf){
        return modelMapper.map(shelf,ResponseShelfDTO.class);
    }

    public static ResponseDeleteShelfDTO convertToResponseDeleteShelfDTO(Shelf shelf){
        return modelMapper.map(shelf,ResponseDeleteShelfDTO.class);
    }
}
