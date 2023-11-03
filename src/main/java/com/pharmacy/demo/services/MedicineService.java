package com.pharmacy.demo.services;

import com.pharmacy.demo.exceptions.BadRequestException;
import com.pharmacy.demo.exceptions.NotFoundException;
import com.pharmacy.demo.models.dao.MedicineDAO;
import com.pharmacy.demo.models.dto.medicineDTO.AddMedicineDTO;
import com.pharmacy.demo.models.dto.medicineDTO.EditMedicineDTO;
import com.pharmacy.demo.models.dto.medicineDTO.FilterMedicineDTO;
import com.pharmacy.demo.models.pojo.*;
import com.pharmacy.demo.models.repository.MedicineRepository;
import com.pharmacy.demo.models.repository.UserRepository;
import com.pharmacy.demo.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class MedicineService {
    private final MedicineRepository medicineRepository;
    private final MedicineDAO medicineDAO;
    private final Utils utils;
    private final ShelfService shelfService;

    public Medicine getById(int userId, int medicineId) {
        List<Medicine> medicineList = getMedicineForPharmacy(userId, medicineId);
        if (medicineList.isEmpty()) {
            throw new NotFoundException("Medicine not found");
        }
        return medicineList.get(0);
    }

    public List<Medicine> addMedicines(AddMedicineDTO addMedicineDTO, int userId) {
        User user=utils.checkWhetherUserHasPharmacy(userId);
        Pharmacy pharmacy = user.getPharmacy();
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

    public Medicine delete(int userId, int medicineId) {
        List<Medicine> medicineList = getMedicineForPharmacy(userId, medicineId);
        if (medicineList.isEmpty()) {
            throw new NotFoundException("Medicine not found");
        }
        medicineRepository.delete(medicineList.get(0));
        return medicineList.get(0);
    }

    public Medicine edit(int userId, EditMedicineDTO editMedicineDTO, int medicineId) {
        List<Medicine> medicineList = getMedicineForPharmacy(userId, medicineId);
        if (medicineList.size() < 1) {
            throw new NotFoundException("Medicine not found");
        }
        Medicine medicine = medicineList.get(0);
        if (editMedicineDTO.getBarcode() != null) {
            medicine.setBarcode(editMedicineDTO.getBarcode());
        }
        if (editMedicineDTO.getName() != null) {
            medicine.setName(editMedicineDTO.getName());
        }
        if (editMedicineDTO.getDetails() != null) {
            medicine.setDetails(editMedicineDTO.getDetails());
        }
        if (editMedicineDTO.getManufacturer() != null) {
            medicine.setManufacturer(editMedicineDTO.getManufacturer());
        }
        if (editMedicineDTO.getStrength() != null) {
            medicine.setStrength(editMedicineDTO.getStrength());
        }
        if (editMedicineDTO.getPrice() != null) {
            medicine.setPrice(editMedicineDTO.getPrice());
        }
        medicineRepository.save(medicine);
        return medicine;
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

    public List<Medicine> getMedicineForPharmacy(int userId, int medicineId) {
        User user=utils.checkWhetherUserHasPharmacy(userId);
        Pharmacy pharmacy = user.getPharmacy();
        return pharmacy.getMedicine().stream().filter(medicine -> medicine.getId() == medicineId)
                .collect(Collectors.toList());
    }

    public List<Medicine> getAllByShelf(int userId, int shelfId) {
        User user= utils.checkWhetherUserHasPharmacy(userId);
        Pharmacy pharmacy = user.getPharmacy();
        List<Shelf> shelfList = pharmacy.getShelfs().stream().filter((shelf -> shelf.getId() == shelfId))
                .collect(Collectors.toList());
        if (shelfList.isEmpty()) {
            throw new NotFoundException("Shelf not found");
        }
        return medicineRepository.getMedicinesByShelfId(shelfId);
    }

    public List<Medicine> getAllByPharmacy(int userId) {
        User user= utils.checkWhetherUserHasPharmacy(userId);
        Pharmacy pharmacy = user.getPharmacy();
        return medicineRepository.getMedicinesByPharmacyId(pharmacy.getId());
    }

    public List<Medicine> filter(int userId, FilterMedicineDTO filterMedicineDTO) {
        return medicineDAO.filterMedicine(userId, filterMedicineDTO);
    }

    public Medicine addToShelf(int userId, int medicineId, int shelfId) {
        Medicine medicine=getById(userId,medicineId);
        Shelf shelf=shelfService.getById(userId,shelfId);
        shelf.getMedicines().add(medicine);
        medicine.setShelf(shelf);
        medicineRepository.save(medicine);
        return medicine;
    }
}
