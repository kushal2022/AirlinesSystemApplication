package edu.miu.edu.cs544.airlinereservationsystem.services;

import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Reservation;

public interface ReservationService {
    Reservation saveReservation(Reservation reservation);
    Reservation getReservation(Long id);
    Reservation updateReservation(Long id, Reservation reservation);
    boolean confirmReservation(Long id);
    boolean cancelReservation(Long id);
}
