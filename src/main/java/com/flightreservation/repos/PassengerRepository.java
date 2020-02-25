package com.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.flightreservation.entity.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

}
