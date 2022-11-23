package com.pharmacy.demo.models.pojo;

import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue
    private int id;
    private String username;
    private String password;
    private String email;
    private String name;
    private Timestamp createTime;
    @ManyToOne
    @JoinColumn("pharmacy_id")
    private Pharmacy pharmacy;
}




