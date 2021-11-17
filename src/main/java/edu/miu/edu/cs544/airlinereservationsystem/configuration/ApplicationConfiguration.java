package edu.miu.edu.cs544.airlinereservationsystem.configuration;

import edu.miu.edu.cs544.airlinereservationsystem.model.ErrorResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public ErrorResponse getErrorResponse() {
        return new ErrorResponse();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
