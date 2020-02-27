package com.flightreservation.repos;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.flightreservation.entity.Flight;
import com.flightreservation.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);

	
	//@Query("from Flight where departureCity=:departureCity and arrivalCity=:arrivalCity and dateOfDeparture=:dateOfDeparture")
	//List<Flight> findFlights(@Param("departureCity") String from, @Param("arrivalCity")String to, @Param("dateOfDeparture")Date departureDate);
	@Query("from User where email=:email")
	 User findBYEmail(@Param("email")String username); 

}
