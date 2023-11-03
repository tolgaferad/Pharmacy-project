package com.pharmacy.demo.controllers;

import com.pharmacy.demo.models.dto.shelfDTO.AddShelfDTO;
import com.pharmacy.demo.models.dto.shelfDTO.ResponseDeleteShelfDTO;
import com.pharmacy.demo.models.dto.shelfDTO.ResponseShelfDTO;
import com.pharmacy.demo.models.pojo.Shelf;
import com.pharmacy.demo.services.ShelfService;
import com.pharmacy.demo.utils.EntityToDTOConverter;
import com.pharmacy.demo.utils.SessionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/shelfs")
@RequiredArgsConstructor
public class ShelfController extends AbstractController {
    private final SessionManager sessionManager;
    private final ShelfService shelfService;

    @PostMapping
    public ResponseShelfDTO addShelf(HttpSession session,
                                     @RequestBody AddShelfDTO addShelfDTO) {
        int userId = sessionManager.getLoggedId(session);
        Shelf shelf = shelfService.addShelf(userId, addShelfDTO);
        return EntityToDTOConverter.convertToResponseShelfDTO(shelf);
    }

    @GetMapping("/{shelf_id}")
    public ResponseShelfDTO getById(HttpSession session,
                                    @PathVariable("shelf_id") int shelfId) {
        int userId = sessionManager.getLoggedId(session);
        Shelf shelf = shelfService.getById(userId, shelfId);
        return EntityToDTOConverter.convertToResponseShelfDTO(shelf);
    }

    @GetMapping("/pharmacy")
    public List<ResponseShelfDTO> getAllByPharmacy(HttpSession session) {
        int userId = sessionManager.getLoggedId(session);
        List<Shelf> shelves = shelfService.getAllByPharmacy(userId);
        return shelves.stream()
                .map(EntityToDTOConverter::convertToResponseShelfDTO)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{shelf_id}")
    public ResponseDeleteShelfDTO delete(HttpSession session,
                                         @PathVariable("shelf_id") int shelfId) {
        int userId = sessionManager.getLoggedId(session);
        Shelf shelf = shelfService.delete(userId, shelfId);
        return EntityToDTOConverter.convertToResponseDeleteShelfDTO(shelf);
    }

}
