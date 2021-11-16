package edu.miu.edu.cs544.airlinereservationsystem.services;

import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Reservation;
import edu.miu.edu.cs544.airlinereservationsystem.model.ReservationRequest;

public interface ReservationService {
    Reservation saveReservation(ReservationRequest reservation);
    Reservation getReservation(Long id);
    Reservation updateReservation(Long id, ReservationRequest reservationRequest);
    void confirmReservation(Long id);
    void cancelReservation(Long id);
}
