package com.pharmacy.demo.services;
import com.pharmacy.demo.models.dto.pharmacyDTO.CreatePharmacyDTO;
import com.pharmacy.demo.models.dto.userDTO.UserOnlyEmailDTO;
import com.pharmacy.demo.models.pojo.Pharmacy;
import com.pharmacy.demo.models.pojo.User;
import com.pharmacy.demo.models.repository.PharmacyRepository;
import com.pharmacy.demo.models.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PharmacyService {
    @Autowired
    private PharmacyRepository pharmacyRepository;
    @Autowired
    private UserRepository userRepository;
    public User addPharmacist(UserOnlyEmailDTO userEmailDTO, int currentUserID){
        //TODO
        return new User();
    }

    public Pharmacy createPharmacy(CreatePharmacyDTO createPharmacyDTO, int userId) {
        //TODO functionality for pharmacy creation
        return  new Pharmacy();
    }
}
