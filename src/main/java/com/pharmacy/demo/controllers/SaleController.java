package com.pharmacy.demo.controllers;

import com.pharmacy.demo.models.dto.saleDTO.AddSaleDTO;
import com.pharmacy.demo.models.dto.saleDTO.ResponseDeleteSaleDTO;
import com.pharmacy.demo.models.dto.saleDTO.ResponseSaleDTO;
import com.pharmacy.demo.models.pojo.Sale;
import com.pharmacy.demo.services.SaleService;
import com.pharmacy.demo.utils.EntityToDTOConverter;
import com.pharmacy.demo.utils.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SaleController extends AbstractController {
    @Autowired
    private SessionManager sessionManager;
    @Autowired
    private SaleService saleService;

    @PostMapping("/sales")
    public ResponseSaleDTO addSale(@Valid @RequestBody AddSaleDTO addSaleDTO,
                                   HttpSession session) {
        int userId = sessionManager.getLoggedId(session);
        Sale sale = saleService.addSale(addSaleDTO, userId);
        return EntityToDTOConverter.convertToResponseSaleDTO(sale);
    }

    @GetMapping("/sales/{sale_id}/medicines/{medicine_id}")
    public ResponseSaleDTO addMedicineToSale(HttpSession session,
                                             @PathVariable("medicine_id") int medicineId,
                                             @PathVariable("sale_id") int saleId) {
        int userId = sessionManager.getLoggedId(session);
        Sale sale = saleService.addMedicine(userId, medicineId, saleId);
        return EntityToDTOConverter.convertToResponseSaleDTO(sale);
    }

    @GetMapping("sales/{sale_id}/confirm")
    public ResponseSaleDTO confirmSale(HttpSession session,
                                       @PathVariable("sale_id") int saleId) {
        int userId = sessionManager.getLoggedId(session);
        Sale sale = saleService.confirmSale(userId, saleId);
        return EntityToDTOConverter.convertToResponseSaleDTO(sale);
    }

    @GetMapping("sales/{sale_id}")
    public ResponseSaleDTO getById(HttpSession session,
                                   @PathVariable("sale_id") int saleId) {
        int userId = sessionManager.getLoggedId(session);
        Sale sale = saleService.getByID(userId, saleId);
        return EntityToDTOConverter.convertToResponseSaleDTO(sale);
    }

    @DeleteMapping("sales/{sale_id}")
    public ResponseDeleteSaleDTO delete(HttpSession session,
                                        @PathVariable("sale_id") int saleId) {
        int userId = sessionManager.getLoggedId(session);
        Sale sale = saleService.deleteById(userId, saleId);
        return EntityToDTOConverter.convertToResponseDeleteSaleDTO(sale);
    }
    @GetMapping("sales/")
    public List<ResponseSaleDTO> getByPharmacy(HttpSession session){
        int userId=sessionManager.getLoggedId(session);
        List<Sale> sales=saleService.getAllByPharmacy(userId);
        return sales.stream()
                .map(EntityToDTOConverter::convertToResponseSaleDTO)
                .collect(Collectors.toList());
    }

}
