package edu.miu.edu.cs544.airlinereservationsystem.database.dto;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Passenger extends User {

    @NotNull
    @OneToOne
    @JoinColumn(name = "address_id")
    private Address addresses;


}
