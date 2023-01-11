package com.pharmacy.demo.controllers;

import com.pharmacy.demo.models.dto.medicineDTO.AddMedicineDTO;
import com.pharmacy.demo.models.dto.medicineDTO.EditMedicineDTO;
import com.pharmacy.demo.models.dto.medicineDTO.FilterMedicineDTO;
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

    @DeleteMapping("/medicines/{medicine_id}")
    public ResponseMedicineDTO deleteById(HttpSession session,
                                          @PathVariable("medicine_id") int medicineId) {
        int userId = sessionManager.getLoggedId(session);
        Medicine medicine = medicineService.delete(userId, medicineId);
        return EntityToDTOConverter.convertToResponsMedicineDTO(medicine);
    }

    @PutMapping("/medicines/{medicine_id}")
    public ResponseMedicineDTO edit(HttpSession session,
                                    @Valid @RequestBody EditMedicineDTO editMedicineDTO,
                                    @PathVariable("medicine_id") int medicineId) {
        int userId = sessionManager.getLoggedId(session);
        Medicine medicine = medicineService.edit(userId, editMedicineDTO, medicineId);
        return EntityToDTOConverter.convertToResponsMedicineDTO(medicine);
    }

    @GetMapping("/medicines/shelfs/{shelf_id}")
    public List<ResponseMedicineDTO> getByShelf(HttpSession session,
                                                @PathVariable("shelf_id") int shelfId) {
        int userId = sessionManager.getLoggedId(session);
        List<Medicine> medicines = medicineService.getAllByShelf(userId, shelfId);
        return medicines.stream()
                .map(EntityToDTOConverter::convertToResponsMedicineDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/medicines/pharmacy")
    public List<ResponseMedicineDTO> getByPharmacy(HttpSession session) {
        int userId = sessionManager.getLoggedId(session);
        List<Medicine> medicines = medicineService.getAllByPharmacy(userId);
        return medicines.stream()
                .map(EntityToDTOConverter::convertToResponsMedicineDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/medicines/filter")
    public List<ResponseMedicineDTO> filter(HttpSession session,
                                            @Valid @RequestBody FilterMedicineDTO filterMedicineDTO) {
        int userId = sessionManager.getLoggedId(session);
        List<Medicine> medicines = medicineService.filter(userId, filterMedicineDTO);
        return medicines.stream()
                .map(EntityToDTOConverter::convertToResponsMedicineDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/medicines/{medicine_id}/shelfs/{shelf_id}")
    public ResponseMedicineDTO addToShelf(HttpSession session,
                                          @PathVariable("medicine_id") int medicineId,
                                          @PathVariable("shelf_id") int shelfId) {
        int userId = sessionManager.getLoggedId(session);
        Medicine medicine = medicineService.addToShelf(userId, medicineId, shelfId);
        return EntityToDTOConverter.convertToResponsMedicineDTO(medicine);
    }
}
