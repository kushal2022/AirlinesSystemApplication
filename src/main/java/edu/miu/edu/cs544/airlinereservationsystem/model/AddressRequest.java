package edu.miu.edu.cs544.airlinereservationsystem.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class AddressRequest {

    @NotNull(message = "street cannot be null")
    @Size(min = 2, max = 200)
    private String street;

    @NotNull(message = "city cannot be null")
    @Size(min = 2, max = 100)
    private String city;

    @NotNull(message = "state cannot be null")
    @Size(min = 2, max = 30)
    private String state;

    @NotNull(message = "zip cannot be null")
    @Size(min = 5, max = 10)
    private String zip;
}
