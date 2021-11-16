package edu.miu.edu.cs544.airlinereservationsystem.model;

import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Address;
import lombok.Data;

@Data
public class AirportRequest {
    private String code;
    private String name;
    private Address address;
}
