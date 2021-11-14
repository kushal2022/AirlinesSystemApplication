package edu.miu.edu.cs544.airlinereservationsystem.database.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade ={CascadeType.PERSIST})
    @JoinColumn(name="passenger_Id")
    private Passenger passenger;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "flight_id")
    private List<Flight> flight;

    @OneToOne(cascade ={CascadeType.PERSIST})
    @JoinColumn(name="agent_Id")
    private Agent agent;

    @Column(nullable = false)
    private boolean isConfirmed = false;

    @Column(nullable = false)
    private Status status = Status.PENDING;

    @Column(nullable = false)
    private boolean isPurchased = false;

    @NotNull(message = "flightDate cannot be null")
    @Column(nullable = false)
    private LocalDate flightDate;
}