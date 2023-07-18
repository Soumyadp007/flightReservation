package com.reservation.reservation.service;

import com.reservation.reservation.entity.Reservation;

import java.util.List;

public interface ReservationService {
    Reservation createReservation(Reservation reservation);

    Reservation updateReservation(long id, Reservation reservation);

    Reservation getResrvationById(long id);

    List<Reservation> getALlReservation(int pageNo, int pageSize, String sortBy, String sortDir);

    List<Reservation> getReservationByFlightNumber(String flightNumber, int pageNo, int pageSize, String sortBy, String sortDir);
}
