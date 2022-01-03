package io.jpe.starter.reservation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.jpe.starter.ThcApiApp;
import io.jpe.starter.location.Location;


@RestController
public class ReservationControl {
	private static final Logger logger = LogManager.getLogger(ReservationControl.class);

	@Autowired
	private ReservationService reservationService;
	@Autowired
    private KafkaTemplate<String, List<Reservation>> kafkaTemplate;

    private static final String TOPIC = "DailyReservations";
	
	@GetMapping("/location/{id}/all-reservations")
	public List<Reservation> allReservationsFromALocation(@PathVariable int id) {
		logger.info("retrieving all reservations at a location");
		List<Reservation> listReservations = null;
		try {
			listReservations = reservationService.getAllReservationsFromLocation(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error(e.getStackTrace().toString());
		}
		return listReservations;
	}
	
	@GetMapping("/location/{id}/reservations")
	public List<Reservation> allReservationsFromALocationPaginationDefault(@PathVariable int id) {
		logger.info("retrieving first page of reservations at a location");
		List<Reservation> listReservations = null;
		try {
			listReservations = reservationService.getReservationsWithPagination(id, 0);
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error(e.getStackTrace().toString());
		}
		return listReservations;
	}
	
	@GetMapping("/location/{id}/reservations/page/{num}")
	public List<Reservation> allReservationsFromALocationPagination(@PathVariable int id, @PathVariable int num) {
		logger.info("retrieving reservations at a location pagewise");
		List<Reservation> listReservations = null;
		try {
			listReservations = reservationService.getReservationsWithPagination(id, num-1);
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error(e.getStackTrace().toString());
		}
		return listReservations;
	}
	
	@GetMapping("/location/{id}/reservation/{reservationId}")
	public Reservation reservationFromALocation(@PathVariable int id, @PathVariable int reservationId) {
		
		logger.info("retrieving a reservation at a location");
		
		List<Reservation> reservations = new ArrayList<>();
		try {
			reservations = reservationService.getAllReservationsFromLocation(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error(e.getStackTrace().toString());
		}
		for (Reservation reservation : reservations) {
			if (reservation.getReservationid() == reservationId)
				return reservation;
		}
		return null;
	}
	
	@PostMapping("/location/{id}/add-reservation")
	public void addReservationToALocation(@RequestBody Reservation reservation, @PathVariable int id ) {
		logger.info("adding a reservation at a location");
		String pattern = "dd/MM/yyyy";
        DateFormat df = new SimpleDateFormat(pattern);
        Date today = new Date();
        String date = df.format(today);
		reservation.setLocation(new Location(id));
		reservation.setDate(date);
		try {
			reservationService.addReservationToLocation(reservation);
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error(e.getStackTrace().toString());
		}
	}
	
	@PostMapping("/location/{id}/add-multiple-reservations-at-location")
	public void addReservationsToALocation(@RequestBody List<Reservation> reservations, @PathVariable int id ) {
		logger.info("adding multiple reservations at a location");
		String pattern = "dd/MM/yyyy";
        DateFormat df = new SimpleDateFormat(pattern);
        Date today = new Date();
        String date = df.format(today);
		for(Reservation reservation: reservations) {
			reservation.setLocation(new Location(id));
			reservation.setDate(date);
			try {
				reservationService.addReservationToLocation(reservation);
			} catch (Exception e) {
				logger.error(e.getMessage());
				logger.error(e.getStackTrace().toString());
			}
		}
	}
	
	@PutMapping("/location/{id}/update-reservation")
	public void updateReservationAtALocation(@RequestBody Reservation reservation, @PathVariable int id ) {
		logger.info("updating a reservation at a location");
		String pattern = "dd/MM/yyyy";
        DateFormat df = new SimpleDateFormat(pattern);
        Date today = new Date();
        String date = df.format(today);
		reservation.setLocation(new Location(id));
		reservation.setDate(date);
		try {
			reservationService.addReservationToLocation(reservation);
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error(e.getStackTrace().toString());
		}
	}
	
	@PutMapping("/location/{id}/update-multiple-reservations")
	public void updateReservationsAtALocation(@RequestBody List<Reservation> reservations, @PathVariable int id ) {
		logger.info("updating multiple reservations at a location");
		String pattern = "dd/MM/yyyy";
        DateFormat df = new SimpleDateFormat(pattern);
        Date today = new Date();
        String date = df.format(today);
		for(Reservation reservation: reservations) {
			reservation.setLocation(new Location(id));
			reservation.setDate(date);
			try {
				reservationService.addReservationToLocation(reservation);
			} catch (Exception e) {
				logger.error(e.getMessage());
				logger.error(e.getStackTrace().toString());
			}
		}
	}
	
	@DeleteMapping("/location/{id}/reservation/{reservationid}")
	public void deleteReservationFromALocation(@PathVariable int id, @PathVariable int reservationid) {
		logger.info("deleting a reservation at a location");
		List<Reservation> reservations = new ArrayList<>();
		reservations = reservationService.getAllReservationsFromLocation(id);
		try {
			for (Reservation reservation : reservations) {
				if (reservation.getReservationid() == reservationid)
					reservationService.deleteReservationFromLocation(reservation);		
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error(e.getStackTrace().toString());
		}	
	}
	
	@GetMapping("/kafka")
	public String allReservationsPerDay() {
		logger.info("retrieving all reservations to publish to kafka");
		String pattern = "dd/MM/yyyy";
        DateFormat df = new SimpleDateFormat(pattern);
        Date today = new Date();
        String date = df.format(today);
		List<Reservation> listReservations = null;
		try {
			listReservations = reservationService.getAllReservationsInADay(date);
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error(e.getStackTrace().toString());
		}
		
		kafkaTemplate.send(TOPIC, listReservations);
		return "Published Successfully" ;
	}
	

}
