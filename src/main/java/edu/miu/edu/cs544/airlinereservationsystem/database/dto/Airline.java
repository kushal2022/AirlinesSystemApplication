package edu.miu.edu.cs544.airlinereservationsystem.database.dto;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@SecondaryTable(name = "History")
public class Airline {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(unique = true, nullable = false)
    @Size(min=2,max=3)
    private String code;

    @NotNull
    @Column(nullable = false)
    private String name;

    @Column(table = "History", length = 2000)
    private String history;
}