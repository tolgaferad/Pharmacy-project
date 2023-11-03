package com.pharmacy.demo.controllers;

import com.pharmacy.demo.models.dto.saleDTO.AddSaleDTO;
import com.pharmacy.demo.models.dto.saleDTO.ResponseDeleteSaleDTO;
import com.pharmacy.demo.models.dto.saleDTO.ResponseSaleDTO;
import com.pharmacy.demo.models.pojo.Sale;
import com.pharmacy.demo.services.SaleService;
import com.pharmacy.demo.utils.EntityToDTOConverter;
import com.pharmacy.demo.utils.SessionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sales")
@RequiredArgsConstructor
public class SaleController extends AbstractController {
    private final SessionManager sessionManager;
    private final SaleService saleService;

    @PostMapping
    public ResponseSaleDTO addSale(@Valid @RequestBody AddSaleDTO addSaleDTO,
                                   HttpSession session) {
        int userId = sessionManager.getLoggedId(session);
        Sale sale = saleService.addSale(addSaleDTO, userId);
        return EntityToDTOConverter.convertToResponseSaleDTO(sale);
    }

    @GetMapping("/{sale_id}/medicines/{medicine_id}")
    public ResponseSaleDTO addMedicineToSale(HttpSession session,
                                             @PathVariable("medicine_id") int medicineId,
                                             @PathVariable("sale_id") int saleId) {
        int userId = sessionManager.getLoggedId(session);
        Sale sale = saleService.addMedicine(userId, medicineId, saleId);
        return EntityToDTOConverter.convertToResponseSaleDTO(sale);
    }

    @GetMapping("/{sale_id}/confirm")
    public ResponseSaleDTO confirmSale(HttpSession session,
                                       @PathVariable("sale_id") int saleId) {
        int userId = sessionManager.getLoggedId(session);
        Sale sale = saleService.confirmSale(userId, saleId);
        return EntityToDTOConverter.convertToResponseSaleDTO(sale);
    }

    @GetMapping("/{sale_id}")
    public ResponseSaleDTO getById(HttpSession session,
                                   @PathVariable("sale_id") int saleId) {
        int userId = sessionManager.getLoggedId(session);
        Sale sale = saleService.getByID(userId, saleId);
        return EntityToDTOConverter.convertToResponseSaleDTO(sale);
    }

    @DeleteMapping("/{sale_id}")
    public ResponseDeleteSaleDTO delete(HttpSession session,
                                        @PathVariable("sale_id") int saleId) {
        int userId = sessionManager.getLoggedId(session);
        Sale sale = saleService.deleteById(userId, saleId);
        return EntityToDTOConverter.convertToResponseDeleteSaleDTO(sale);
    }
    @GetMapping
    public List<ResponseSaleDTO> getByPharmacy(HttpSession session){
        int userId=sessionManager.getLoggedId(session);
        List<Sale> sales=saleService.getAllByPharmacy(userId);
        return sales.stream()
                .map(EntityToDTOConverter::convertToResponseSaleDTO)
                .collect(Collectors.toList());
    }
    //TODO Add functionality for deletion of medicine from sale and adding it back to the medicine list of pharmacy

}
