package edu.miu.edu.cs544.airlinereservationsystem.model;

import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class RegisterRequest {


    @NotNull(message = "lastName cannot be null")
    @NotBlank(message = "lastName cannot be empty")
    private String firstName;

    @NotNull(message = "lastName cannot be null")
    @NotBlank(message = "lastName cannot be empty")
    private String lastName;

    @NotNull(message = "date of birth cannot be null")
    @Past
    private LocalDate dob;

    @NotNull(message = "email cannot be null")
    @NotBlank(message = "email cannot be empty")
    @Email
    private String email;

    @NotNull(message = "username cannot be null")
    @NotBlank(message = "username cannot be empty")
    private String username;

    @Size(min = 4, max = 20)
    @NotNull(message = "password cannot be null")
    @NotBlank(message = "password cannot be empty")
    @Pattern(regexp = "(?=.*?[0-9])(?=.*?[A-Za-z]).+", message = "password must contain at least  one character and number")
    private String password;

    @NotNull
    private AddressRequest address;


    @NotNull(message = "role cannot be null")
    @NotBlank(message = "role cannot be empty")
    private Role role;
}
