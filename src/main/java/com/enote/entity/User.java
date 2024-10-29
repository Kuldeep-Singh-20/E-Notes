package com.enote.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "enote")
@Data
public class User {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;
    private String name ;
    private String qualification ;
    private String email ;
    private String address;
    private String gender;
    private String password;
    private String role;

}
