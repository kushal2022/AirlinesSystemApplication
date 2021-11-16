package edu.miu.edu.cs544.airlinereservationsystem.model;

import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Agent;
import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Flight;
import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Passenger;
import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Status;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ReservationRequest {
    private Passenger passenger;
    private List<Flight> flight;
    private Agent agent;
    private boolean isConfirmed = false;
    private Status status = Status.PENDING;
    private boolean isPurchased = false;
    private LocalDate flightDate;
}
