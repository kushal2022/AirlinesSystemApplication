package edu.miu.edu.cs544.airlinereservationsystem.services;

import edu.miu.edu.cs544.airlinereservationsystem.database.dao.*;
import edu.miu.edu.cs544.airlinereservationsystem.database.dto.*;
import edu.miu.edu.cs544.airlinereservationsystem.model.ReservationRequest;
import edu.miu.edu.cs544.airlinereservationsystem.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    Utils utils;


    @Override
    public Reservation saveReservation(ReservationRequest reservationRequest) {
        log.info("saveReservation - creating new reservationRequest . . .");

        Passenger passenger = passengerRepository.getById(reservationRequest.getPassenger().getId());
        Agent agent = agentRepository.getById(reservationRequest.getAgent().getId());

        List<Flight> flights = new ArrayList<>();
        List<Flight> flightList = reservationRequest.getFlight();
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
        myReservation.setConfirmed(reservationRequest.isConfirmed());
        myReservation.setStatus(reservationRequest.getStatus());
        myReservation.setPurchased(reservationRequest.isPurchased());
        myReservation.setFlightDate(reservationRequest.getFlightDate());

        log.info("saveReservation - reservationRequest: {}", myReservation);
        return reservationRepository.save(myReservation);
    }

    @Override
    public Reservation getReservation(Long id) {
        log.info("getReservation - getting reservation by id: {}", id);
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);
        Reservation reservation = optionalReservation.isPresent() ? optionalReservation.get() : null;
        log.info("getReservation - Reservation. reservation: {}", reservation);
        return reservation;
    }

    @Override
    public Reservation updateReservation(Long id, ReservationRequest reservationRequest) {
        log.info("updateReservation - updating reservationRequest id: {}", id);

        Optional<Reservation> findReservationById = reservationRepository.findById(id);
        if (findReservationById.isPresent()) {
            Reservation oldReservation = findReservationById.get();
            Reservation newReservation = new Reservation();
            newReservation.setId(oldReservation.getId());
            newReservation.setPassenger(oldReservation.getPassenger());
            newReservation.setFlight(reservationRequest.getFlight());
            newReservation.setAgent(reservationRequest.getAgent());
            newReservation.setConfirmed(reservationRequest.isConfirmed());
            newReservation.setStatus(reservationRequest.getStatus());
            newReservation.setPurchased(reservationRequest.isPurchased());
            newReservation.setFlightDate(reservationRequest.getFlightDate());
            log.info("updateReservation - Saving reservationRequest: {}", newReservation);
            return reservationRepository.save(newReservation);
        }
        return null;
    }

    @Override
    public void confirmReservation(Long id) {
        reservationRepository.confirmReservation(id);

        //once optionalReservation confirmed create ticket for each flight
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);

        if (optionalReservation.isPresent()) {
            log.info("Generate ticket foreach flight");
            Reservation reservation = optionalReservation.get();
            List<Flight> flights = reservation.getFlight();
            for (Flight flight : flights) {
                Ticket ticket = new Ticket();
                String number = utils.generateTicketNumber();
                System.out.println(number);
                ticket.setNumber(number);
                LocalDate flightDate = reservation.getFlightDate();
                LocalTime departureTime = flight.getDepartureTime();
                LocalDateTime localDateTime = LocalDateTime.of(flightDate, departureTime);
                ticket.setFlightDate(localDateTime);
                ticket.setReservation(reservation);
                ticket.setFlight(flight);

                ticketRepository.save(ticket);
            }
        }
    }

    @Override
    public void cancelReservation(Long id) {
        reservationRepository.cancelReservation(id);
    }
}
