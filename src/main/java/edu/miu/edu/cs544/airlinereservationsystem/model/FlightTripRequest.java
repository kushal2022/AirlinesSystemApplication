package edu.miu.edu.cs544.airlinereservationsystem.model;

import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Airport;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FlightTripRequest {
    private Airport departureAirport;
    private Airport arrivalAirport;
    private LocalDate flightDate;
}
