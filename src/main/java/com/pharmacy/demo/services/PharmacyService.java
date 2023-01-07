package com.pharmacy.demo.services;

import com.pharmacy.demo.exceptions.BadRequestException;
import com.pharmacy.demo.exceptions.NotFoundException;
import com.pharmacy.demo.exceptions.UnauthorizedException;
import com.pharmacy.demo.models.dto.pharmacyDTO.EditPharmacyDTO;
import com.pharmacy.demo.models.dto.pharmacyDTO.PharmacyDTO;
import com.pharmacy.demo.models.dto.userDTO.UserOnlyEmailDTO;
import com.pharmacy.demo.models.pojo.Pharmacy;
import com.pharmacy.demo.models.pojo.User;
import com.pharmacy.demo.models.repository.PharmacyRepository;
import com.pharmacy.demo.models.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PharmacyService {
    @Autowired
    private PharmacyRepository pharmacyRepository;
    @Autowired
    private UserRepository userRepository;

    public User addPharmacist(UserOnlyEmailDTO userEmailDTO, int currentUserId) {
        User loggedUser = checkWhetherIsAdmin(currentUserId);
        Pharmacy pharmacy = loggedUser.getPharmacy();
        if (pharmacy == null) {
            throw new NotFoundException("Pharmacy for user not found");
        }
        String email = userEmailDTO.getEmail();
        User userByEmail = userRepository.findByEmail(email);
        if (userByEmail == null) {
            throw new NotFoundException("User with this email not found");
        }
        if (userByEmail.getRole().equals("ADMIN")) {
            throw new BadRequestException("You cannot add admins of other pharmacies");
        }
        if(userByEmail.getPharmacy()!=null) {
            throw new BadRequestException("User already has pharmacy");
        }
        pharmacy.getUsers().add(userByEmail);
        userByEmail.setPharmacy(pharmacy);
        pharmacyRepository.save(pharmacy);
        return userByEmail;
    }

    public Pharmacy createPharmacy(PharmacyDTO pharmacyDTO, int userId) {
        User user = checkWhetherIsAdmin(userId);
        if (user.getPharmacy() != null) {
            throw new BadRequestException("This user already has pharmacy");
        }
        Pharmacy pharmacy = new Pharmacy(pharmacyDTO);
        user.setPharmacy(pharmacy);
        pharmacyRepository.save(pharmacy);
        return pharmacy;
    }

    public User deletePharmacist(int userId, int pharmacistId) {
        User user = checkWhetherIsAdmin(userId);
        int pharmacyId = user.getPharmacy().getId();
        User userToDelete = userRepository.findByIdAndPharmacyId(pharmacistId, pharmacyId);
        if (userToDelete == null) {
            throw new NotFoundException("User not found for this pharmacy");
        }
        if (userId == userToDelete.getId()) {
            throw new BadRequestException("You cannot delete yourself");
        }
        user.getPharmacy().getUsers().remove(userToDelete);
        userToDelete.setPharmacy(null);
        userRepository.save(userToDelete);
        return userToDelete;
    }

    public List<User> getAllPharmacists(int userId) {
        User user = checkWhetherIsAdmin(userId);
        if(user.getPharmacy()==null){
            throw new BadRequestException("User doesn't have pharmacy");
        }
        if(user.getPharmacy().getUsers()==null){
            throw new BadRequestException("Pharmacy doesn't have pharmacists");
        }
        return user.getPharmacy().getUsers().stream().filter(u -> u.getRole().equals("USER")).collect(Collectors.toList());
    }

    public Pharmacy edit(EditPharmacyDTO pharmacyDTO, int userId) {
        User user = checkWhetherIsAdmin(userId);
        Pharmacy pharmacy = user.getPharmacy();

        pharmacy.setName(pharmacyDTO.getName());
        pharmacy.setAddress(pharmacy.getAddress());
        pharmacyRepository.save(pharmacy);
        return pharmacy;
    }

    private User checkWhetherIsAdmin(int userId) {
        Optional<User> optUser = userRepository.findById(userId);
        if (optUser.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        User user = optUser.get();
        if (!user.getRole().equals("ADMIN")) {
            throw new UnauthorizedException("User is not authorized for this operation");
        }
        return user;
    }

    public Pharmacy getByUserId(int userId) {
        Optional<User> optUser = userRepository.findById(userId);
        if (optUser.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        User user = optUser.get();
        if(user.getPharmacy()==null){
            throw new BadRequestException("User doesn't have pharmacy");
        }
        return user.getPharmacy();
    }
}
