package com.pharmacy.demo.utils;

import com.pharmacy.demo.models.dto.medicineDTO.ResponseMedicineDTO;
import com.pharmacy.demo.models.dto.pharmacyDTO.PharmacyDTO;
import com.pharmacy.demo.models.dto.userDTO.UserWithoutPasswordDTO;
import com.pharmacy.demo.models.pojo.Medicine;
import com.pharmacy.demo.models.pojo.Pharmacy;
import com.pharmacy.demo.models.pojo.User;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
}
