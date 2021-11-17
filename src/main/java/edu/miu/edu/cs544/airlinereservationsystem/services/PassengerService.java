package edu.miu.edu.cs544.airlinereservationsystem.services;

import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Passenger;
import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Reservation;

import java.util.List;

public interface PassengerService {
    List<Reservation> getMyReservation(Long id);

    Passenger addPassenger(Passenger passenger);
    List<Passenger> getPassengers();
    Passenger getPassenger(Long id);
    void updatePassenger(Long id, Passenger passenger);
    void deletePassenger(Long id);

    Passenger findByUsername(String passengerUsername);
}
