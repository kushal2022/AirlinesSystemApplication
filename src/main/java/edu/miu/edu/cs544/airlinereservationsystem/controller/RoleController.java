package edu.miu.edu.cs544.airlinereservationsystem.controller;

import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Role;
import edu.miu.edu.cs544.airlinereservationsystem.services.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Role>> getRoles() {
        List<Role> roles = service.getRoles();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity<Void> updateRole(@PathVariable Long id, @Validated @RequestBody Role role) {
        service.updateRole(id, role);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        service.deleteRole(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
