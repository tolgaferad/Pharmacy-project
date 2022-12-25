package com.pharmacy.demo.controllers;

import com.pharmacy.demo.models.dto.medicineDTO.AddMedicineDTO;
import com.pharmacy.demo.models.dto.medicineDTO.ResponseMedicineDTO;
import com.pharmacy.demo.models.pojo.Medicine;
import com.pharmacy.demo.services.MedicineService;
import com.pharmacy.demo.utils.EntityToDTOConverter;
import com.pharmacy.demo.utils.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class MedicineController extends AbstractController {
    @Autowired
    private SessionManager sessionManager;
    @Autowired
    private MedicineService medicineService;

    @GetMapping("/medicines/{medicine_id}")
    public ResponseMedicineDTO getById(HttpSession session,
                                       @PathVariable(name = "medicine_id") int medicineId) {
        int userId = sessionManager.getLoggedId(session);
        Medicine medicine = medicineService.getById(userId, medicineId);
        return new ResponseMedicineDTO(medicine);
    }

    @PostMapping("/medicines")
    public List<ResponseMedicineDTO> addMedicine(@Valid @RequestBody AddMedicineDTO addMedicineDTO,
                                                 HttpSession session) {
        int userId = sessionManager.getLoggedId(session);
        List<Medicine> medicines = medicineService.addMedicine(addMedicineDTO, userId);
        return medicines.stream()
                .map(EntityToDTOConverter::convertToResponsMedicineDTO)
                .collect(Collectors.toList());
    }
}
