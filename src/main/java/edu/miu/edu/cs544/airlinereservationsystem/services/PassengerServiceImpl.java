package edu.miu.edu.cs544.airlinereservationsystem.services;

import edu.miu.edu.cs544.airlinereservationsystem.database.dao.PassengerRepository;
import edu.miu.edu.cs544.airlinereservationsystem.database.dao.ReservationRepository;
import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Passenger;
import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Reservation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PassengerServiceImpl implements PassengerService {

    private static final Logger log = LoggerFactory.getLogger(PassengerServiceImpl.class);

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    PassengerRepository passengerRepository;

    @Override
    public List<Passenger> getPassengers() {
        return passengerRepository.findAll();
    }

    @Override
    public List<Reservation> getMyReservation(Long id) {

        List<Reservation> reservationList = reservationRepository.getReservationByPassengerId(id);
        log.info("\n\nhere: {}", reservationList.size());
        for (Reservation reservation : reservationList) {
            log.info("reservation: {}", reservation);
        }
        return reservationList;
    }

    @Override
    public Passenger addPassenger(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    @Override
    public Passenger getPassenger(Long id) {
        return passengerRepository.findById(id).orElse(null);
    }

    @Override
    public void updatePassenger(Long id, Passenger passenger) {
        Optional<Passenger> optionalPassenger = passengerRepository.findById(id);
        if (optionalPassenger.isPresent()) {
            Passenger oldPassenger = optionalPassenger.get();
            oldPassenger.setAddresses(passenger.getAddresses());
            oldPassenger.setFirstName(passenger.getFirstName());
            oldPassenger.setLastName(passenger.getLastName());
            oldPassenger.setDob(passenger.getDob());
            oldPassenger.setEmail(passenger.getEmail());
            oldPassenger.setUsername(passenger.getUsername());
            oldPassenger.setPassword(passenger.getPassword());
            passengerRepository.save(oldPassenger);
        }
    }

    @Override
    public void deletePassenger(Long id) {
        passengerRepository.deleteById(id);
    }

    @Override
    public Passenger findByUsername(String passengerUsername) {
        return passengerRepository.findByUsername(passengerUsername);
    }
}
