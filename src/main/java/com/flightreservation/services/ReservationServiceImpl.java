package com.flightreservation.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.flightreservation.dto.ReservationRequest;
import com.flightreservation.entity.Flight;
import com.flightreservation.entity.Passenger;
import com.flightreservation.entity.Reservation;
import com.flightreservation.repos.FlightRepository;
import com.flightreservation.repos.PassengerRepository;
import com.flightreservation.repos.ReservationRepository;
import com.flightreservation.util.EmailUtil;
import com.flightreservation.util.PDFGenerator;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Value("${com.sharad.flightreservation.itinerary.dirpath}")
	private String ITINERARI_DIR = "D:\\reservation";
	@Autowired
	private FlightRepository flightRepository;
	@Autowired
	private PassengerRepository passengerRepository;
	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private PDFGenerator pdfGenerator;
	
	@Autowired
	private EmailUtil emailUtil;
	
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
		String filePath = ITINERARI_DIR+saveREservation.getId()+".pdf";
		pdfGenerator.generateItinerory(saveREservation, filePath);
		emailUtil.sendItinerary(passenger.getMail(), filePath);
		
		return saveREservation;
	}

}
