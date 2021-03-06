package edu.miu.edu.cs544.airlinereservationsystem.database.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@SecondaryTable(name = "History")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Airline {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "airline code cannot be null")
    @Column(unique = true, nullable = false)
    @Size(min=2,max=3)
    private String code;

    @NotNull(message = "airline name cannot be null")
    @Column(nullable = false)
    private String name;

    @Column(table = "History", length = 2000)
    private String history;
}