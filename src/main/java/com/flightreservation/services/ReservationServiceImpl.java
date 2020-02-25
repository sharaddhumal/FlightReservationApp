package com.flightreservation.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightreservation.dto.ReservationRequest;
import com.flightreservation.entity.Flight;
import com.flightreservation.entity.Passenger;
import com.flightreservation.entity.Reservation;
import com.flightreservation.repos.FlightRepository;
import com.flightreservation.repos.PassengerRepository;
import com.flightreservation.repos.ReservationRepository;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private FlightRepository flightRepository;
	@Autowired
	private PassengerRepository passengerRepository;
	@Autowired
	private ReservationRepository reservationRepository;
	@Override
	public Reservation bookFlight(ReservationRequest request) {

		//make Payment
		Long flightId = request.getFlightId();
		Optional<Flight> fl = flightRepository.findById(flightId);
		Flight flight=null;
		if(fl.isPresent()) {
			flight = fl.get();
		}
		
		Passenger passenger = new Passenger();
		passenger.setFisrtName(request.getPassengerFirstName());
		passenger.setLastName(request.getPassengerLastName());
		passenger.setPhone(request.getPassengerPhone());
		passenger.setMail(request.getPassengerEmail());
		
		Passenger savedPassenger = passengerRepository.save(passenger);
		
		Reservation reservation = new Reservation();
		reservation.setFlight(flight);
		reservation.setPassenger(savedPassenger);
		reservation.setCheckedIn(false);
		
		Reservation saveREservation = reservationRepository.save(reservation);
		return null;
	}

}
