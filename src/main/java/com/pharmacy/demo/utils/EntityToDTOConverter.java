package com.pharmacy.demo.utils;

import com.pharmacy.demo.models.dto.userDTO.UserWithoutPasswordDTO;
import com.pharmacy.demo.models.pojo.User;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class EntityToDTOConverter {

    private static ModelMapper modelMapper;

    public static UserWithoutPasswordDTO convertToUserWithoutPass(User user) {
        return modelMapper.map(user, UserWithoutPasswordDTO.class);
    }
}
