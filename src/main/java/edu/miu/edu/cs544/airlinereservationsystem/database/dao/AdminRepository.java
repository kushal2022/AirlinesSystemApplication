package edu.miu.edu.cs544.airlinereservationsystem.database.dao;

import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface AdminRepository extends JpaRepository<Admin, Long> {
}
