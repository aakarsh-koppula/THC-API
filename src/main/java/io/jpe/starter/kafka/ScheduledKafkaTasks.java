package io.jpe.starter.kafka;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import io.jpe.starter.reservation.Reservation;

import io.jpe.starter.reservation.ReservationService;

@Component
public class ScheduledKafkaTasks {
	private static final Logger logger = LogManager.getLogger(ScheduledKafkaTasks.class);

	@Autowired
	private ReservationService reservationService;
	@Autowired
    private KafkaTemplate<String, List<Reservation>> kafkaTemplate;

    private static final String TOPIC = "DailyReservations";
    
    @Scheduled(cron = "0 0 23 * * *")
    
    public void publishAllReservationsOfDay() {
    	
		logger.info("retrieving all reservations to publish to kafka as per schedule");
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
		logger.info("successfully retrieved reservations of the day");
		
		kafkaTemplate.send(TOPIC, listReservations);
		
		logger.info("Published to Kafka successfully");
	}
}
