package com.selenium.test.flights.reservation.model;

public record FlightReservationTestData (String firstName,
                                         String lastName,
                                         String email,
                                         String password,
                                         String street,
                                         String city,
                                         String zip,
                                         int passengersCount,
                                         String expectedPrice){
}
