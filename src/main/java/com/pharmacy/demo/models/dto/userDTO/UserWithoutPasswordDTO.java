package com.pharmacy.demo.models.dto.userDTO;

import com.pharmacy.demo.models.pojo.Pharmacy;
import com.pharmacy.demo.models.pojo.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
@Getter
@Setter
@NoArgsConstructor
@Component
public class UserWithoutPasswordDTO {
    private String username;
    private String email;
    private String name;
    private Timestamp createTime;
    private Pharmacy pharmacy;

    public UserWithoutPasswordDTO(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.name = user.getName();
        this.createTime = user.getCreateTime();
        this.pharmacy=user.getPharmacy();
    }
}
