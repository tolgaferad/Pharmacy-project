package com.pharmacy.demo.services;

import com.pharmacy.demo.exceptions.BadRequestException;
import com.pharmacy.demo.models.dto.shelfDTO.AddShelfDTO;
import com.pharmacy.demo.models.pojo.Pharmacy;
import com.pharmacy.demo.models.pojo.Sale;
import com.pharmacy.demo.models.pojo.Shelf;
import com.pharmacy.demo.models.pojo.User;
import com.pharmacy.demo.models.repository.ShelfRepository;
import com.pharmacy.demo.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShelfService {
    @Autowired
    private Utils utils;
    @Autowired
    private ShelfRepository shelfRepository;
    public Shelf addShelf(int userId, AddShelfDTO addShelfDTO) {
        User user= utils.checkWhetherUserHasPharmacy(userId);
        Pharmacy pharmacy = user.getPharmacy();
        List<Shelf> shelfList = pharmacy.getShelfs().stream().filter(shelf1 -> shelf1.getName().equals(addShelfDTO.getName()))
                .collect(Collectors.toList());
        if(!shelfList.isEmpty()){
            throw new BadRequestException("Shelf with such shelf name already exists");
        }
        Shelf shelf=new Shelf(addShelfDTO);
        pharmacy.getShelfs().add(shelf);
        shelf.setPharmacy(pharmacy);
        shelfRepository.save(shelf);
        return shelf;
    }

    public Shelf getById(int userId, int shelfId) {
        User user= utils.checkWhetherUserHasPharmacy(userId);
        Pharmacy pharmacy = user.getPharmacy();
        List<Shelf> shelfList = pharmacy.getShelfs().stream().filter(shelf1 -> shelf1.getId()==shelfId).collect(Collectors.toList());
        if(shelfList.isEmpty()){
            throw new BadRequestException("Shelf not found");
        }
        return shelfList.get(0);
    }

    public List<Shelf> getAllByPharmacy(int userId) {
        User user= utils.checkWhetherUserHasPharmacy(userId);
        Pharmacy pharmacy = user.getPharmacy();
        return pharmacy.getShelfs();
    }

    public Shelf delete(int userId, int shelfId) {
        Shelf shelf=getById(userId,shelfId);
        shelfRepository.deleteById(shelf.getId());
        return shelf;
    }
    //TODO
}
