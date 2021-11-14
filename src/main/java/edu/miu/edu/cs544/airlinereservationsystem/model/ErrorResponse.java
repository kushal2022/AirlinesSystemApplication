package edu.miu.edu.cs544.airlinereservationsystem.model;

import lombok.Data;

@Data
public class ErrorResponse {
    private int code;
    private String message;
}
