package edu.miu.edu.cs544.airlinereservationsystem.controller;

import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Reservation;
import edu.miu.edu.cs544.airlinereservationsystem.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService service;

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Long> createReservation(@Validated @RequestBody Reservation reservation) {
        Reservation savedReservation = service.saveReservation(reservation);
        return new ResponseEntity<>(savedReservation.getId(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Reservation> getReservationDetails(@PathVariable Long id) {
        Reservation reservation = service.getReservation(id);
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity<Void> updateRole(@PathVariable Long id, @Validated @RequestBody Reservation reservation) {
        service.updateReservation(id, reservation);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ResponseEntity<Void> confirmReservation(@PathVariable Long id) {
        service.confirmReservation(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> cancelReservation(@PathVariable Long id) {
        service.cancelReservation(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
