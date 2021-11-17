package edu.miu.edu.cs544.airlinereservationsystem.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import edu.miu.edu.cs544.airlinereservationsystem.model.RegisterRequest;
import edu.miu.edu.cs544.airlinereservationsystem.services.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/users")
public class RegistrationController {
    
    @Autowired
    UserRegistrationService userService;


    @RequestMapping(method = RequestMethod.POST)
    @HystrixCommand(fallbackMethod = "createUserFallback")
    public ResponseEntity<String> createUser(@RequestBody RegisterRequest user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    public ResponseEntity<String> createUserFallback() {
        return new ResponseEntity<>("Unable to create user. Please retry later!", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
