package com.enote.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Notes {


    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;
    private String title;
    private String description;
    private LocalDate date;

    @ManyToOne
    private User user;
}
