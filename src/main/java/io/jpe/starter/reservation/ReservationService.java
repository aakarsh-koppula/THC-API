package io.jpe.starter.reservation;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import io.jpe.starter.menuitem.MenuItem;


@Service
public class ReservationService {
	@Autowired
	private ReservationRepository reservationRepository;
	private static final Logger logger = LogManager.getLogger(ReservationService.class);
	
	
	public void addReservationToLocation(Reservation item) {
		logger.info("function to save a reservation at a location");
		try {
			reservationRepository.save(item);
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error(e.getStackTrace().toString());
		}
	}

	public List<Reservation> getAllReservationsFromLocation(int id) {
		logger.info("function to retrieve all reservations at a location");
		List<Reservation> reservationlistlist = new ArrayList<>();
		try {
			reservationRepository.findAllByLocationId(id).forEach(reservationlistlist::add);
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error(e.getStackTrace().toString());
		}
		
		return reservationlistlist;
	}
	
	public void deleteReservationFromLocation(Reservation item) {
		logger.info("function to delete a reservation at a location");
		try {
			reservationRepository.delete(item);
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error(e.getStackTrace().toString());
		}
	}
	
	public List<Reservation> getReservationsWithPagination(int id, int offset){
		logger.info("function to retrieve reservation at a location pagewise");
        List<Reservation> reservations = null;
		try {
			reservations = reservationRepository.findAllByLocationId(id, PageRequest.of(offset, 3));
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error(e.getStackTrace().toString());
		}
        return  reservations;
	}
}
