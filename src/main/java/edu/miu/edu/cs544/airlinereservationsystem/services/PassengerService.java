package edu.miu.edu.cs544.airlinereservationsystem.services;

import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Reservation;

import java.util.List;

public interface PassengerService {
    List<Reservation> getMyReservation(Long id);
}
