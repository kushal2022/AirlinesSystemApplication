package edu.miu.edu.cs544.airlinereservationsystem.database.dao;

import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Modifying
    @Query("UPDATE Reservation SET isConfirmed = 1 WHERE id = ?1")
    void confirmReservation(Long id);

    @Modifying
    @Query("UPDATE Reservation SET status = '1' WHERE id = ?1")
    void cancelReservation(Long id);

}
