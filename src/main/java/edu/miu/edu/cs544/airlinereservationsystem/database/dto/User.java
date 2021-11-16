package edu.miu.edu.cs544.airlinereservationsystem.database.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "lastName cannot be null")
    @NotBlank(message = "lastName cannot be empty")
    @Size(min = 1)
    private String firstName;

    @NotNull(message = "lastName cannot be null")
    @NotBlank(message = "lastName cannot be empty")
    @Size(min = 1)
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
    @Column(unique=true)
    private String username;

    @Size(min = 4, max = 20)
    @NotNull(message = "password cannot be null")
    @NotBlank(message = "password cannot be empty")
    @Pattern(regexp = "(?=.*?[0-9])(?=.*?[A-Za-z]).+", message = "password must contain at least  one character and number")
    private String password;
}
