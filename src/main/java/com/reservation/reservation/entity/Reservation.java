package com.reservation.reservation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "reservation_tb")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name= "passenger_id")
    private String passengerId;
    @Column(name= "flight_number")
    private String flightNumber;
    private String status;

}
