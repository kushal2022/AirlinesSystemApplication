package edu.miu.edu.cs544.airlinereservationsystem.controller;

import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Role;
import edu.miu.edu.cs544.airlinereservationsystem.services.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    RoleServiceImpl service;

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Long> createRole(@Validated @RequestBody Role role) {

        Role savedRole = service.saveRole(role);
        return new ResponseEntity<>(savedRole.getId(), HttpStatus.CREATED);
    }


}
