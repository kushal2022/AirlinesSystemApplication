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
    @Future
    private LocalDate flightDate;

    @NonNull
    @ManyToOne()
    @JoinColumn(name="reservationCode",referencedColumnName = "code")
    private Reservation reservation;

    @NonNull
    @ManyToOne()
    private Flight flight;
}
