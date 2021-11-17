package edu.miu.edu.cs544.airlinereservationsystem.database.dao;

import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface PassengerRepository extends JpaRepository<Passenger, Long> {

    @Query(value = "SELECT DISTINCT p.* FROM Reservation r JOIN Reservation_Passenger rp ON r.id = rp.reservation_id\n" +
            "JOIN Passenger p ON p.id= rp.passenger_id  WHERE r.agent_id = ?1", nativeQuery = true)
    List<Passenger> getPassengersByAgent(Long id);
}
