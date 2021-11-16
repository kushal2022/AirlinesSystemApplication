package edu.miu.edu.cs544.airlinereservationsystem.services;

import edu.miu.edu.cs544.airlinereservationsystem.database.dao.ReservationRepository;
import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Reservation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerServiceImpl implements PassengerService {

    private static final Logger log = LoggerFactory.getLogger(PassengerServiceImpl.class);

    @Autowired
    ReservationRepository reservationRepository;

    @Override
    public List<Reservation> getMyReservation(Long id) {

        List<Reservation> reservationList = reservationRepository.getReservationByPassengerId(id);
        log.info("\n\nhere: {}", reservationList.size());
        for (Reservation reservation : reservationList) {
            log.info("reservation: {}", reservation);
        }
        return reservationList;
    }
}
