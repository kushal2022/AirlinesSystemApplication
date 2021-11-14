package edu.miu.edu.cs544.airlinereservationsystem.database.dto;


import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    @Size(min = 3, max = 3)
    @NonNull
    private String code;

    @NonNull
    @Size(min = 2, max = 20)
    private String name;

    @NonNull
    @OneToOne(cascade = {CascadeType.PERSIST})
    private Address address;
}
