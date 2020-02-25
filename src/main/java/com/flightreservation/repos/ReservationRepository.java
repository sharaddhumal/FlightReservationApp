package com.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flightreservation.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
