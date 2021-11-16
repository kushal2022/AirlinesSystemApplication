package edu.miu.edu.cs544.airlinereservationsystem.controller;

import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Reservation;
import edu.miu.edu.cs544.airlinereservationsystem.services.PassengerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/passenger")
public class PassengerController {

    @Autowired
    PassengerServiceImpl passengerService;

    @RequestMapping(value = "/{id}/reservations", method = RequestMethod.GET)
    public ResponseEntity<List<Reservation>> getMyReservation(@PathVariable Long id) {
        List<Reservation> reservations = passengerService.getMyReservation(id);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }
}
