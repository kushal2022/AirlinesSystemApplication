package edu.miu.edu.cs544.airlinereservationsystem.database.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "flight number cannot be null")
    @Column(unique = true, nullable = false)
    private String number;

    @Column(nullable = false)
    private int capacity;

    @NotNull(message = "airline cannot be null")
    @OneToOne(cascade = CascadeType.PERSIST)
    private Airline airline;

    @NotNull(message = "departureAirport cannot be null")
    @OneToOne(cascade = CascadeType.PERSIST)
    private Airport departureAirport;

    @NotNull(message = "arrivalAirport cannot be null")
    @OneToOne(cascade = CascadeType.PERSIST)
    private Airport arrivalAirport;

    @NotNull(message = "departureTime cannot be null")
//    @Future
    private LocalTime departureTime;

    @NotNull(message = "arrivalTime cannot be null")
//    @Future
    private LocalTime arrivalTime;
}
