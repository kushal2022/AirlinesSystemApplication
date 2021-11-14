package edu.miu.edu.cs544.airlinereservationsystem.database.dto;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public abstract class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany
    @JoinColumn(name = "role_id")
    private List<Role> roles;
}
