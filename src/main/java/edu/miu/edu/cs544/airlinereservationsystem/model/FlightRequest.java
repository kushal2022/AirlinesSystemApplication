package edu.miu.edu.cs544.airlinereservationsystem.model;

import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Airline;
import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Airport;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Data
public class FlightRequest {


    @NotNull(message = "flight number cannot be null")
    private String number;

    private int capacity;

    @NotNull(message = "airline cannot be null")
    private Long airlineId;

    @NotNull(message = "departureAirport cannot be null")
    private Long departureAirportId;

    @NotNull(message = "arrivalAirport cannot be null")
    private Long arrivalAirportId;

    @NotNull(message = "departureTime cannot be null")
    private LocalTime departureTime;

    @NotNull(message = "arrivalTime cannot be null")
    private LocalTime arrivalTime;
}
