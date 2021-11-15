package edu.miu.edu.cs544.airlinereservationsystem.services;

import edu.miu.edu.cs544.airlinereservationsystem.database.dao.RoleRepository;
import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository repository;

    @Override
    public Role saveRole(Role role) {
        return repository.save(role);
    }

    @Override
    public List<Role> getRoles() {
        return repository.findAll();
    }

    @Override
    public void updateRole(Long id, Role role) {
        Optional<Role> oldRole = repository.findById(id);
        if (oldRole.isPresent()) {
            Role currentRole = oldRole.get();
            currentRole.setName(role.getName());
            repository.save(currentRole);
        }
    }

    @Override
    public void deleteRole(Long id) {
        repository.deleteById(id);
    }
}
