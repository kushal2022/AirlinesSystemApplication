package edu.miu.edu.cs544.airlinereservationsystem.database.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NoArgsConstructor
@Data
@Entity
public class Ticket {
    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @Column(unique = true)
    @Digits(fraction = 0, integer = 20)
    private String number;

    @NonNull
    @Future(message = "Flight date must be to the future")
    private LocalDate flightDate;

    @NonNull
    @ManyToOne
    @JoinTable(name = "Ticker_Reservation")
    private Reservation reservation;

    @NonNull
    @OneToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;
}
