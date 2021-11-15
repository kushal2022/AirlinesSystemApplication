package edu.miu.edu.cs544.airlinereservationsystem.services;

import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Role;

import java.util.List;

public interface RoleService {
    Role saveRole(Role role);
    List<Role> getRoles();
    void updateRole(Long id, Role role);
    void deleteRole(Long id);
}
