package edu.miu.edu.cs544.airlinereservationsystem.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthenticationRequest {

    public String userName;
    public String password;
    public Role role;

    public AuthenticationRequest(String userName, String password, Role role) {
        this.userName = userName;
        this.password = password;
        this.role = role;
    }
}
