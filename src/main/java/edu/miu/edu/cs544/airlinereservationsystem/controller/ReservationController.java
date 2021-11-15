package edu.miu.edu.cs544.airlinereservationsystem.controller;

import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Reservation;
import edu.miu.edu.cs544.airlinereservationsystem.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

//    @RequestMapping(method = RequestMethod.GET)
//    public ResponseEntity<List<Role>> getRoles() {
//        List<Role> roles = service.getRoles();
//        return new ResponseEntity<>(roles, HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
//    public ResponseEntity<Void> updateRole(@PathVariable Long id, @Validated @RequestBody Role role) {
//        service.updateRole(id, role);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
//        service.deleteRole(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

}
