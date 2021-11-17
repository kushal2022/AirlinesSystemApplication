package edu.miu.edu.cs544.airlinereservationsystem.services;

import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Admin;

import java.util.List;

public interface AdminService {

    Admin addAdmin(Admin admin);
    List<Admin> getAdmins();
    Admin getAdmin(Long id);
    void updateAdmin(Long id, Admin admin);
    void deleteAdmin(Long id);
}
