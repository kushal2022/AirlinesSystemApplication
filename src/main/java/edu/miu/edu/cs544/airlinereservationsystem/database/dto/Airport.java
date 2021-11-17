package edu.miu.edu.cs544.airlinereservationsystem.database.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "airport code cannot be null")
    @Column(unique = true, nullable = false)
    @Size(min = 3, max = 3)
    private String code;

    @NotNull(message = "airport name cannot be null")
    @Size(min = 2, max = 20)
    private String name;

    @NotNull(message = "address cannot be null")
    @OneToOne(cascade = {CascadeType.ALL})
    private Address address;

    public Airport(Long id) {
        this.id = id;
    }
}
