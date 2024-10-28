package com.lil.spring_ex.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query(value = "SELECT * FROM RESERVATION WHERE DATE(RES_DATE) = DATE(?1)", nativeQuery = true)
    Iterable<Reservation> findReservationByReservationDate(java.util.Date reservationDate);
}
