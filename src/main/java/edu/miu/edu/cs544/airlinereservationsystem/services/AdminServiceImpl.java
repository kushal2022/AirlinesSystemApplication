package edu.miu.edu.cs544.airlinereservationsystem.services;

import edu.miu.edu.cs544.airlinereservationsystem.database.dao.AdminRepository;
import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminRepository adminRepository;

    @Override
    public Admin addAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    public List<Admin> getAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public Admin getAdmin(Long id) {
        return adminRepository.findById(id).orElse(null);
    }

    @Override
    public void updateAdmin(Long id, Admin passenger) {
        Optional<Admin> optionalAdmin = adminRepository.findById(id);
        if (optionalAdmin.isPresent()) {
            Admin oldAdmin = optionalAdmin.get();
            oldAdmin.setFirstName(passenger.getFirstName());
            oldAdmin.setLastName(passenger.getLastName());
            oldAdmin.setDob(passenger.getDob());
            oldAdmin.setEmail(passenger.getEmail());
            oldAdmin.setUsername(passenger.getUsername());
            oldAdmin.setPassword(passenger.getPassword());
            adminRepository.save(oldAdmin);
        }
    }

    @Override
    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }
}