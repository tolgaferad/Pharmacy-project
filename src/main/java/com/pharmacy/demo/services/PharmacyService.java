package com.pharmacy.demo.services;
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

@Service
public class PharmacyService {
    @Autowired
    private PharmacyRepository pharmacyRepository;
    @Autowired
    private UserRepository userRepository;
    //TODO methods functionalities
    public User addPharmacist(UserOnlyEmailDTO userEmailDTO, int currentUserID){

        return new User();
    }

    public Pharmacy createPharmacy(PharmacyDTO pharmacyDTO, int userId) {

        return  new Pharmacy();
    }

    public User deletePharmacist(int userId, int pharmacistId) {
        return  new User();
    }

    public List<User> getAllPharmacists(int userId) {
        return new ArrayList<User>();
    }

    public Pharmacy edit(PharmacyDTO pharmacyDTO, int userId) {
        return new Pharmacy();
    }
}
