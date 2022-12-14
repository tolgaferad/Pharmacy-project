package com.pharmacy.demo.controllers;


import com.pharmacy.demo.models.dto.pharmacyDTO.CreatePharmacyDTO;
import com.pharmacy.demo.models.dto.userDTO.UserOnlyEmailDTO;
import com.pharmacy.demo.models.dto.userDTO.UserWithoutPasswordDTO;
import com.pharmacy.demo.models.pojo.Pharmacy;
import com.pharmacy.demo.models.pojo.User;
import com.pharmacy.demo.services.PharmacyService;
import com.pharmacy.demo.utils.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
public class PharmacyController {
    @Autowired
    private SessionManager sessionManager;
    @Autowired
    private PharmacyService pharmacyService;
    @PostMapping("/pharmacy")
    public Pharmacy createPharmacy(CreatePharmacyDTO createPharmacyDTO,HttpSession session){
        int userId=sessionManager.getLoggedId(session);
        return pharmacyService.createPharmacy(createPharmacyDTO,userId);
    }
    @PostMapping("/pharmacy/pharmacist")
    public UserWithoutPasswordDTO addPharmacist(UserOnlyEmailDTO userEmailDTO,HttpSession session) {
        int userId = sessionManager.getLoggedId(session);
        User user = pharmacyService.addPharmacist(userEmailDTO, userId);
        return new UserWithoutPasswordDTO(user);
    }
}
