package com.pharmacy.demo.services;

import com.pharmacy.demo.exceptions.BadRequestException;
import com.pharmacy.demo.exceptions.NotFoundException;
import com.pharmacy.demo.models.dto.saleDTO.AddSaleDTO;
import com.pharmacy.demo.models.pojo.*;
import com.pharmacy.demo.models.repository.SaleDetailRepository;
import com.pharmacy.demo.models.repository.SaleRepository;
import com.pharmacy.demo.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SaleService {
    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private SaleDetailRepository saleDetailRepository;
    @Autowired
    private Utils utils;
    @Autowired
    MedicineService medicineService;

    public Sale addSale(AddSaleDTO addSaleDTO, int userId) {
        User user = utils.checkWhetherUserHasPharmacy(userId);
        Pharmacy pharmacy = user.getPharmacy();
        if (addSaleDTO.getName() != null) {
            addSaleDTO.setName(addSaleDTO.getName());
        }
        Sale sale = new Sale(addSaleDTO);
        sale.setCreateTime(new Timestamp(System.currentTimeMillis()));
        sale.setPharmacy(pharmacy);
        saleRepository.save(sale);
        return sale;
    }

    public Sale addMedicine(int userId, int medicineId, int saleId) {
        Sale sale = getSaleIfExist(userId, saleId);
        List<Medicine> medicines = medicineService.getMedicineForPharmacy(userId, medicineId);
        if (medicines.isEmpty()) {
            throw new NotFoundException("Medicine not found");
        }
        medicineService.delete(userId,medicineId);
        SaleDetail saleDetail = new SaleDetail(medicines.get(0));
        sale.getSaleDetails().add(saleDetail);
        int salePrice = sale.getPrice();
        salePrice += saleDetail.getPrice();
        sale.setPrice(salePrice);
        saleRepository.save(sale);
        saleDetail.setSale(sale);
        saleDetailRepository.save(saleDetail);
        return sale;
    }

    public Sale confirmSale(int userId, int saleId) {
        Sale sale = getSaleIfExist(userId, saleId);
        if (sale.isConfirmed()) {
            throw new BadRequestException("Sale is already confirmed");
        }
        List<Integer> medicineIds = sale.getSaleDetails().stream()
                .map(SaleDetail::getMedicineId)
                .collect(Collectors.toList());
        for (int medicineId : medicineIds) {
            medicineService.delete(userId, medicineId);
        }
        sale.setConfirmed(true);
        saleRepository.save(sale);
        return sale;
    }


    public Sale getByID(int userId, int saleId) {
        return getSaleIfExist(userId, saleId);
    }

    public Sale deleteById(int userId, int saleId) {
        Sale sale = getSaleIfExist(userId, saleId);
        saleRepository.delete(sale);
        return sale;
    }

    private Sale getSaleIfExist(int userId, int saleId) {
        User user = utils.checkWhetherUserHasPharmacy(userId);
        Pharmacy pharmacy = user.getPharmacy();
        List<Sale> sales = pharmacy.getSales().stream().filter(sale -> sale.getId() == saleId)
                .collect(Collectors.toList());
        if (sales.isEmpty()) {
            throw new NotFoundException("Sale not found");
        }
        return sales.get(0);
    }
    public List<Sale> getAllByPharmacy(int userId) {
        User user= utils.checkWhetherUserHasPharmacy(userId);
        Pharmacy pharmacy = user.getPharmacy();
        return saleRepository.getSalesByPharmacyId(pharmacy.getId());
    }
}
