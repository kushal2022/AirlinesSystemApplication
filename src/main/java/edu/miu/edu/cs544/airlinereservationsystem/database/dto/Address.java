package edu.miu.edu.cs544.airlinereservationsystem.database.dto;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 2, max = 200)
    private String street;
    @NonNull
    @Size(min = 2, max = 100)
    private String city;
    @NonNull
    @Size(min = 2, max = 30)
    private String state;

    @NonNull
    @Size(min = 5, max = 10)
    private String zip;
}
