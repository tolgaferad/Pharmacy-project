package com.pharmacy.demo.models.dto.userDTO;

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
    private int id;
    private String username;
    private String email;
    private String name;
    private Timestamp createTime;
    private String role;
    private int pharmacyId;

    public UserWithoutPasswordDTO(User user) {
        this.id=user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.name = user.getName();
        this.createTime = user.getCreateTime();
        this.role=user.getRole();
        if(user.getPharmacy()!=null){
            this.pharmacyId=user.getPharmacy().getId();
        }
    }
}
