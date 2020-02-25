package com.flightreservation.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.flightreservation.entity.Flight;
import com.flightreservation.repos.FlightRepository;

@Controller
public class ReservationController {

	@Autowired
	private FlightRepository flightRepository;
	
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
}
