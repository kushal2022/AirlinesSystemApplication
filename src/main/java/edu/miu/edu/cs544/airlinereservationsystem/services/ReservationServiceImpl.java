package edu.miu.edu.cs544.airlinereservationsystem.services;

import edu.miu.edu.cs544.airlinereservationsystem.database.dao.AgentRepository;
import edu.miu.edu.cs544.airlinereservationsystem.database.dao.FlightRepository;
import edu.miu.edu.cs544.airlinereservationsystem.database.dao.PassengerRepository;
import edu.miu.edu.cs544.airlinereservationsystem.database.dao.ReservationRepository;
import edu.miu.edu.cs544.airlinereservationsystem.database.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {

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
        System.out.println(reservation.toString());

        Passenger passenger = passengerRepository.getById(reservation.getPassenger().getId());
        System.out.println("Real passenger: " + passenger.toString());

        Agent agent = agentRepository.getById(reservation.getAgent().getId());
        System.out.println("Real agent: " + agent.toString());

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

        System.out.println("Final flight: " + flights);


        Reservation myReservation = new Reservation();
        myReservation.setPassenger(passenger);
        myReservation.setFlight(flights);
        myReservation.setAgent(agent);
        myReservation.setConfirmed(reservation.isConfirmed());
        myReservation.setStatus(reservation.getStatus());
        myReservation.setPurchased(reservation.isPurchased());
        myReservation.setFlightDate(reservation.getFlightDate());

        System.out.println(myReservation.toString());

        return reservationRepository.save(myReservation);
    }
}
