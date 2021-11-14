package edu.miu.edu.cs544.airlinereservationsystem.database.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "street cannot be null")
    @Size(min = 2, max = 200)
    private String street;

    @NotNull(message = "city cannot be null")
    @Size(min = 2, max = 100)
    private String city;

    @NotNull(message = "state cannot be null")
    @Size(min = 2, max = 30)
    private String state;

    @NotNull(message = "zip cannot be null")
    @Size(min = 5, max = 10)
    private String zip;
}
