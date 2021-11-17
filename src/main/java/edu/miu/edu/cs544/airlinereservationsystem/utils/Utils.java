package edu.miu.edu.cs544.airlinereservationsystem.utils;

import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Random;

@Service
public class Utils {

    public String generateTicketNumber() {
        long number = 1234567 + new Random().nextInt(8888888);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp.getTime() + "" + number;
    }
}