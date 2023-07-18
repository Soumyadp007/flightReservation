package com.reservation.reservation.controller;

import com.reservation.reservation.entity.Reservation;
import com.reservation.reservation.payload.Flight;
import com.reservation.reservation.payload.Passenger;
import com.reservation.reservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private RestTemplate restTemplate;
    //http://localhost:7070/api/reservation
    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation){
        ResponseEntity<Flight> flightResponse = restTemplate.getForEntity("http://localhost:8080/api/flight/" + reservation.getFlightNumber(), Flight.class);
        if(flightResponse.getStatusCode()!= HttpStatus.OK || flightResponse.getBody()== null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Flight flight = flightResponse.getBody();

        ResponseEntity<Passenger> passengerResponse = restTemplate.getForEntity("http://localhost:9090/api/passenger/" + reservation.getPassengerId(), Passenger.class);
        if(passengerResponse.getStatusCode()!= HttpStatus.OK || passengerResponse.getBody()== null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Passenger passenger = passengerResponse.getBody();
        Reservation confirmReservation = reservationService.createReservation(reservation);
        return new ResponseEntity<>(confirmReservation, HttpStatus.CREATED);

    }
    @PutMapping
    public ResponseEntity<Reservation> updateReservation(@RequestBody Reservation reservation, @PathVariable("id") long id){
        Reservation updatedReservation = reservationService.updateReservation(id, reservation);
        return new ResponseEntity<>(updatedReservation, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable("id") long id){
        Reservation reservation = reservationService.getResrvationById(id);
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }
    @GetMapping
    public List<Reservation> getAllReservation(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize" ,defaultValue = "5", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "ASC", required = false) String sortDir
    ){
        List<Reservation> aLlReservation = reservationService.getALlReservation(pageNo, pageSize, sortBy, sortDir);
        return aLlReservation;
    }
    @GetMapping("/{flightNumber}")
    public List<Reservation> getReservationByFlightNumber(
            @PathVariable("flightNumber") String flightNumber,
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize" ,defaultValue = "5", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "ASC", required = false) String sortDir
    ){
        return reservationService.getReservationByFlightNumber(flightNumber, pageNo, pageSize, sortBy, sortDir);
    }

}
