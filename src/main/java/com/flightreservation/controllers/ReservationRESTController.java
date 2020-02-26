package com.flightreservation.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightreservation.dto.ReservationUpdateRequest;
import com.flightreservation.entity.Reservation;
import com.flightreservation.repos.ReservationRepository;

@RestController
@CrossOrigin
public class ReservationRESTController {

	@Autowired
	private ReservationRepository reservationRepository;
	
	@RequestMapping("/reservations/{id}")
	public Reservation findReservation(@PathVariable("id")Long id) {

		Optional<Reservation> res = reservationRepository.findById(id);
		Reservation reservation=null;
		if(res.isPresent()) {
			reservation = res.get();
		}
		return reservation;
	}
	
	@RequestMapping("/reservations")
	public Reservation updateReservation(@RequestBody ReservationUpdateRequest request) {
		Optional<Reservation> res = reservationRepository.findById(request.getId());
		Reservation reservation=null;
		if(res.isPresent()) {
			reservation = res.get();
		}
		reservation.setNumberOfBags(request.getNumberOfBags());
		reservation.setCheckedIn(request.getCheckedIn());
		return reservationRepository.save(reservation);
	}
}
