package edu.miu.edu.cs544.airlinereservationsystem.model;

import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Flight;
import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Reservation;
import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class TicketRequest {

    @NotNull
    @Digits(fraction = 0, integer = 20)
    private String number;

    @NotNull
    @Future
    private LocalDate flightDate;

    @NotNull
    private Reservation reservation;

}
