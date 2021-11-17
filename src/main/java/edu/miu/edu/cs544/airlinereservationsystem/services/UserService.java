package edu.miu.edu.cs544.airlinereservationsystem.services;

import edu.miu.edu.cs544.airlinereservationsystem.model.RegisterRequest;

public interface UserService {
    String createUser(RegisterRequest user);
}
