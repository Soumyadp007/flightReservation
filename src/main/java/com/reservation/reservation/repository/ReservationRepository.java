package com.reservation.reservation.repository;

import com.reservation.reservation.entity.Reservation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByFlightNumber(String flightNumber, Pageable pageable);
}
