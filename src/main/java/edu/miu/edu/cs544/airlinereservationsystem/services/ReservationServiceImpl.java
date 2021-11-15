package edu.miu.edu.cs544.airlinereservationsystem.services;

import edu.miu.edu.cs544.airlinereservationsystem.database.dao.AgentRepository;
import edu.miu.edu.cs544.airlinereservationsystem.database.dao.FlightRepository;
import edu.miu.edu.cs544.airlinereservationsystem.database.dao.PassengerRepository;
import edu.miu.edu.cs544.airlinereservationsystem.database.dao.ReservationRepository;
import edu.miu.edu.cs544.airlinereservationsystem.database.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {

    private static final Logger log = LoggerFactory.getLogger(ReservationServiceImpl.class);

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    PassengerRepository passengerRepository;

    @Autowired
    AgentRepository agentRepository;

    @Autowired
    FlightRepository flightRepository;


    @Override
    public Reservation saveReservation(Reservation reservation) {
        log.info("saveReservation - creating new reservation . . .");

        Passenger passenger = passengerRepository.getById(reservation.getPassenger().getId());
        Agent agent = agentRepository.getById(reservation.getAgent().getId());

        List<Flight> flights = new ArrayList<>();
        List<Flight> flightList = reservation.getFlight();
        if (!flightList.isEmpty()) {
            for (Flight f : flightList) {
                Optional<Flight> optionalFlight = flightRepository.findById(f.getId());
                if (optionalFlight.isPresent()) {
                    Flight flight = optionalFlight.get();
                    flights.add(flight);
                }
            }
        }

        Reservation myReservation = new Reservation();
        myReservation.setPassenger(passenger);
        myReservation.setFlight(flights);
        myReservation.setAgent(agent);
        myReservation.setConfirmed(reservation.isConfirmed());
        myReservation.setStatus(reservation.getStatus());
        myReservation.setPurchased(reservation.isPurchased());
        myReservation.setFlightDate(reservation.getFlightDate());

        log.info("saveReservation - reservation: {}", myReservation);
        return reservationRepository.save(myReservation);
    }

    @Override
    public Reservation getReservation(Long id) {
        log.info("getReservation - getting reservation by id: {}", id);
        Optional<Reservation> reservation = reservationRepository.findById(id);
        log.info("getReservation - Reservation. reservation: {}", reservation);
        return reservation.get();
    }

    @Override
    public Reservation updateReservation(Long id, Reservation reservation) {
        log.info("updateReservation - updating reservation id: {}", id);

        Optional<Reservation> findReservationById = reservationRepository.findById(id);
        if (findReservationById.isPresent()) {
            Reservation oldReservation = findReservationById.get();
            Reservation newReservation = new Reservation();
            newReservation.setId(oldReservation.getId());
            newReservation.setPassenger(oldReservation.getPassenger());
            newReservation.setFlight(reservation.getFlight());
            newReservation.setAgent(reservation.getAgent());
            newReservation.setConfirmed(reservation.isConfirmed());
            newReservation.setStatus(reservation.getStatus());
            newReservation.setPurchased(reservation.isPurchased());
            newReservation.setFlightDate(reservation.getFlightDate());
            log.info("updateReservation - Saving reservation: {}", newReservation);
            return reservationRepository.save(newReservation);
        }

        return null;
    }

    @Override
    public boolean confirmReservation(Long id) {
        reservationRepository.confirmReservation(id);
        return true;
    }

    @Override
    public boolean cancelReservation(Long id) {
        reservationRepository.cancelReservation(id);
        return true;
    }
}
