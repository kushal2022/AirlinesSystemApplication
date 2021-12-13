package edu.miu.edu.cs544.airlinereservationsystem.database.dao;

import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Modifying
    @Query("UPDATE Reservation SET isConfirmed = 1 WHERE id = ?1")
    void confirmReservation(Long id);

    @Modifying
    @Query("UPDATE Reservation SET status = '1' WHERE id = ?1")
    void cancelReservation(Long id);


    @Query(value = "SELECT DISTINCT * FROM Reservation AS r JOIN Reservation_Passenger AS rp ON r.id = rp.reservation_id WHERE rp.passenger_Id = ?1", nativeQuery = true)
    List<Reservation> getReservationByPassengerId(Long passenger_Id);

    @Query(value = "SELECT DISTINCT * FROM Reservation r \n" +
            "JOIN Reservation_Passenger rp ON r.id = rp.reservation_id\n" +
            "JOIN Passenger p ON rp.passenger_id = p.id\n" +
            "WHERE r.agent_id = ?1", nativeQuery = true)
    List<Reservation> getReservationByAgentId(Long agent);

}
