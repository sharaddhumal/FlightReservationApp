package com.flightreservation.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.stereotype.Component;

import com.flightreservation.entity.Reservation;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Component
public class PDFGenerator {
	
	public void generateItinerory(Reservation reservation, String filePath) {
		Document document = new Document();
		
		String imagePath = "D:\\sharad.png";
		Image image = null;
		try {
			PdfWriter.getInstance(document, new FileOutputStream(filePath));
			document.open();
			
			
			Font font = FontFactory.getFont(FontFactory.COURIER, 20, BaseColor.DARK_GRAY);
			Chunk chunk = new Chunk("Flight Reservation Application", font);
			document.add(chunk);
			
			image = Image.getInstance(imagePath);
			image.scaleToFit(150, 150);
			image.setAbsolutePosition(450f, 10f);
			document.add(image);
			
			
			document.add(generateTable(reservation));
			
			document.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private PdfPTable generateTable(Reservation reservation) {

		PdfPTable table = new PdfPTable(2);
		
		PdfPCell cell;
		
		cell = new PdfPCell(new Phrase("Flight Itinerary "));
		cell.setColspan(2);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Flight Details "));
		cell.setColspan(2);
		table.addCell(cell);
		
		table.addCell("Airlines");
		table.addCell(reservation.getFlight().getOperatingAirlines());
		
		table.addCell("Departure City");
		table.addCell(reservation.getFlight().getDepartureCity());
		
		table.addCell("Arrival City");
		table.addCell(reservation.getFlight().getArrivalCity());
		
		table.addCell("Flight Number");
		table.addCell(reservation.getFlight().getFlightNumber());
		
		table.addCell("Departure Date");
		table.addCell(reservation.getFlight().getDateOfDeparture().toString());
		
		table.addCell("Departure Time");
		table.addCell(reservation.getFlight().getEstimatedDepartureTime().toString());
		
		cell = new PdfPCell(new Phrase("Passenger Details "));
		cell.setColspan(2);
		table.addCell(cell);
		
		table.addCell("First Name");
		table.addCell(reservation.getPassenger().getFisrtName());
		
		table.addCell("Last Name");
		table.addCell(reservation.getPassenger().getLastName());
		
		table.addCell("Email");
		table.addCell(reservation.getPassenger().getMail());
		
		table.addCell("Phone");
		table.addCell(reservation.getPassenger().getPhone());
		
		return table;
	}
}
