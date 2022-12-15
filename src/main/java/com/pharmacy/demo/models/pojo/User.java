package com.pharmacy.demo.models.pojo;
import com.pharmacy.demo.models.dto.userDTO.UserRegisterDTO;
import lombok.*;
import javax.persistence.*;
import java.sql.Timestamp;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private String email;
    private String name;
    private Timestamp createTime;
    private String role;
    @ManyToOne
    @JoinColumn(name="pharmacy_id")
    private Pharmacy pharmacy;

    public User(UserRegisterDTO userDTO) {
        this.username=userDTO.getUsername();
        this.password=userDTO.getPassword();
        this.email=userDTO.getEmail();
        this.name=userDTO.getName();
        this.role=userDTO.getRole();
        this.createTime=userDTO.getCreateTime();
    }
}




