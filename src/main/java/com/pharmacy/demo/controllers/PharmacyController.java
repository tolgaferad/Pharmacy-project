package com.pharmacy.demo.controllers;


import com.pharmacy.demo.models.dto.pharmacyDTO.EditPharmacyDTO;
import com.pharmacy.demo.models.dto.pharmacyDTO.PharmacyDTO;
import com.pharmacy.demo.models.dto.userDTO.UserOnlyEmailDTO;
import com.pharmacy.demo.models.dto.userDTO.UserWithoutPasswordDTO;
import com.pharmacy.demo.models.pojo.Pharmacy;
import com.pharmacy.demo.models.pojo.User;
import com.pharmacy.demo.services.PharmacyService;
import com.pharmacy.demo.utils.EntityToDTOConverter;
import com.pharmacy.demo.utils.SessionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pharmacy")
@RequiredArgsConstructor
public class PharmacyController extends AbstractController {
    private final SessionManager sessionManager;
    private final PharmacyService pharmacyService;


    @PostMapping
    public Pharmacy createPharmacy(@Valid @RequestBody PharmacyDTO pharmacyDTO, HttpSession session) {
        int userId = sessionManager.getLoggedId(session);
        return pharmacyService.createPharmacy(pharmacyDTO, userId);
    }

    @PostMapping("/pharmacist")
    public UserWithoutPasswordDTO addPharmacist(@Valid @RequestBody UserOnlyEmailDTO userEmailDTO, HttpSession session) {
        int userId = sessionManager.getLoggedId(session);
        User user = pharmacyService.addPharmacist(userEmailDTO, userId);
        return new UserWithoutPasswordDTO(user);
    }

    @DeleteMapping("/pharmacist/{pharmacist_id}")
    public UserWithoutPasswordDTO deletePharmacist(@PathVariable(name = "pharmacist_id") int pharmacistId, HttpSession session) {
        int userId = sessionManager.getLoggedId(session);
        User user = pharmacyService.deletePharmacist(userId, pharmacistId);
        return new UserWithoutPasswordDTO(user);
    }

    @GetMapping("/pharmacist")
    public List<UserWithoutPasswordDTO> getAllPharmacists(HttpSession session) {
        int userId = sessionManager.getLoggedId(session);
        List<User> users = pharmacyService.getAllPharmacists(userId);
        return users.stream()
                .map(EntityToDTOConverter::convertToUserWithoutPass)
                .collect(Collectors.toList());
    }

    @PutMapping
    public PharmacyDTO edit(@Valid @RequestBody EditPharmacyDTO pharmacyDTO, HttpSession session) {
        int userId = sessionManager.getLoggedId(session);
        Pharmacy pharmacy= pharmacyService.edit(pharmacyDTO,userId);
        return EntityToDTOConverter.convertToPharmacyDTO(pharmacy);
    }
    @GetMapping
    public PharmacyDTO getById(HttpSession session){
        int userId=sessionManager.getLoggedId(session);
        Pharmacy pharmacy=pharmacyService.getByUserId(userId);
        return EntityToDTOConverter.convertToPharmacyDTO(pharmacy);
    }

}
