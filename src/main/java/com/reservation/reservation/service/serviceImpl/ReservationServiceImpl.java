package com.reservation.reservation.service.serviceImpl;

import com.reservation.reservation.entity.Reservation;
import com.reservation.reservation.exception.ResponseNotFoundException;
import com.reservation.reservation.repository.ReservationRepository;
import com.reservation.reservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private ReservationRepository reservationRepo;
    @Override
    public Reservation createReservation(Reservation reservation) {
        return reservationRepo.save(reservation);
    }

    @Override
    public Reservation updateReservation(long id, Reservation reservation) {
        Reservation reservations = reservationRepo.findById(id).orElseThrow(
                () -> new ResponseNotFoundException("Reservation not found with id:" + id)
        );
        reservation.setId(id);
        Reservation save = reservationRepo.save(reservation);
        return save;
    }

    @Override
    public Reservation getResrvationById(long id) {
        Reservation reservations = reservationRepo.findById(id).orElseThrow(
                () -> new ResponseNotFoundException("Reservation not found with id:" + id)
        );
        return reservations;
    }

    @Override
    public List<Reservation> getALlReservation(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort= sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable= PageRequest.of(pageNo, pageSize, sort);
        Page<Reservation> all = reservationRepo.findAll(pageable);
        List<Reservation> content = all.getContent();
        return content;
    }

    @Override
    public List<Reservation> getReservationByFlightNumber(String flightNumber, int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort= sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable= PageRequest.of(pageNo, pageSize, sort);
        List<Reservation> byFlightNumber = reservationRepo.findByFlightNumber(flightNumber, pageable);
        return byFlightNumber;
    }
}
