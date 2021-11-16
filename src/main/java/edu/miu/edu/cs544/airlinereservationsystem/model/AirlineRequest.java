package edu.miu.edu.cs544.airlinereservationsystem.model;
import lombok.Data;

@Data
public class AirlineRequest {
    private String code;
    private String name;
    private String history;

}