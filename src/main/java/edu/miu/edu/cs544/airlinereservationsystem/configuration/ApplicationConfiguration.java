package edu.miu.edu.cs544.airlinereservationsystem.configuration;

import edu.miu.edu.cs544.airlinereservationsystem.model.ErrorResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public ErrorResponse getErrorResponse() {
        return new ErrorResponse();
    }
}
