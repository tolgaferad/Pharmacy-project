package com.pharmacy.demo.services;

import com.pharmacy.demo.exceptions.BadRequestException;
import com.pharmacy.demo.exceptions.NotFoundException;
import com.pharmacy.demo.models.dto.medicineDTO.AddMedicineDTO;
import com.pharmacy.demo.models.dto.medicineDTO.ResponseMedicineDTO;
import com.pharmacy.demo.models.dto.userDTO.UserOnlyEmailDTO;
import com.pharmacy.demo.models.pojo.Medicine;
import com.pharmacy.demo.models.pojo.Pharmacy;
import com.pharmacy.demo.models.pojo.User;
import com.pharmacy.demo.models.repository.MedicineRepository;
import com.pharmacy.demo.models.repository.PharmacyRepository;
import com.pharmacy.demo.models.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class MedicineService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    MedicineRepository medicineRepository;

    public Medicine getById(int userId, int medicineId) {
        Optional<User> optUser = userRepository.findById(userId);
        if (optUser.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        Pharmacy pharmacy = optUser.get().getPharmacy();
        List<Medicine> medicineList = pharmacy.getMedicine().stream().filter(medicine -> medicine.getId() == medicineId).collect(Collectors.toList());
        if (medicineList.size() < 1) {
            throw new NotFoundException("Medicine not found");
        }
        return medicineList.get(0);
    }

    public List<Medicine> addMedicine(AddMedicineDTO addMedicineDTO, int userId) {
        Optional<User> optUser = userRepository.findById(userId);
        if (optUser.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        Pharmacy pharmacy = optUser.get().getPharmacy();
        if (addMedicineDTO.getPrice() < 0) {
            throw new BadRequestException("Price cannot be less then 0");
        }
        if (addMedicineDTO.getQuantity() < 0) {
            throw new BadRequestException("Quantity cannot be less then 0");
        }

        List<Medicine> medicines = new ArrayList<>();
        for (int i = 0; i < addMedicineDTO.getQuantity(); i++) {
            Medicine medicine = new Medicine(addMedicineDTO);
            String barcode = generateRandom12DigitNumber();
            medicine.setBarcode(barcode);
            pharmacy.getMedicine().add(medicine);
            medicines.add(medicine);
            medicine.setPharmacy(pharmacy);
        }
        medicineRepository.saveAll(medicines);
        return medicines;
    }

    private String generateRandom12DigitNumber() {
        Random random = new Random();
        StringBuilder sbBarcodeNumber = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            int number = random.nextInt(10);
            sbBarcodeNumber.append(number);
        }
        return sbBarcodeNumber.toString();
    }
}
