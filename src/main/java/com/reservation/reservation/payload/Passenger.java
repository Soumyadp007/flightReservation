package com.reservation.reservation.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Passenger {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private long mobile;
}
