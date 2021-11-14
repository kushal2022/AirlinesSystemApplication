package edu.miu.edu.cs544.airlinereservationsystem.database.dto;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Size;
import java.time.LocalTime;

@Entity
@Data
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(unique = true, nullable = false)
    private String number;

    @Size(min = 1, max = 10000)
    private int capacity;

    @NotNull
    @OneToOne(cascade = CascadeType.PERSIST)
    private Airline airline;

    @NotNull
    @OneToOne(cascade = CascadeType.PERSIST)
    private Airport departureAirport;

    @NotNull
    @OneToOne(cascade = CascadeType.PERSIST)
    private Airport arrivalAirport;

    @NotNull
    @Future
    private LocalTime departureTime;

    @NotNull
    @Future
    private LocalTime arrivalTime;

}
