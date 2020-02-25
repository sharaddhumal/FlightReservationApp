package com.flightreservation.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.flightreservation.dto.ReservationRequest;
import com.flightreservation.entity.Flight;
import com.flightreservation.entity.Reservation;
import com.flightreservation.repos.FlightRepository;
import com.flightreservation.services.ReservationService;

@Controller
public class ReservationController {

	//this is Reservation controller
	@Autowired
	private FlightRepository flightRepository;
	
	@Autowired
	private ReservationService reservationSrvice;
	
	@RequestMapping("/showCompleteReservation")
	public String showCompleteReservation(@RequestParam("flightId")Long flightId, ModelMap modelMap) {

		Optional<Flight> flight1 = flightRepository.findById(flightId);
		
		
		if(flight1.isPresent())
		{
			Flight flight = flight1.get();
			modelMap.addAttribute("flight",flight);
		}
		return "completeReservation";
	}
	
	@RequestMapping(value ="/completeReservation",method = RequestMethod.POST)
	public String CompleteReservation(ReservationRequest request, ModelMap modelMap) {
	
		Reservation reservation = reservationSrvice.bookFlight(request);
		System.out.println(reservation);
		modelMap.addAttribute("msg","Reservation Created Successsfully"+reservation.getId());
		return "reservationConfirmation";
		
	}
}
