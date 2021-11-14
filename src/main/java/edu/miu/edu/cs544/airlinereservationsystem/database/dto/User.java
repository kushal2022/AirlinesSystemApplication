package edu.miu.edu.cs544.airlinereservationsystem.database.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    @Size(min = 1)
    private String firstName;

    @NonNull
    @Size(min = 1)
    private String lastName;

    @NotNull
    @Past
    private LocalDate dob;

    @NotNull
    @Email
    private String email;

    @NotNull
    @OneToMany
    @JoinColumn(name = "address_id")
    private List<Address> addresses;
}
