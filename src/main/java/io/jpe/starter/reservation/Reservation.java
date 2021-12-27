package io.jpe.starter.reservation;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import io.jpe.starter.location.Location;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
@Entity
public class Reservation {
	@Id
	private int reservationid;
	private String name;
	private String reservationdate;
	private String reservationtime;
	private String date;
	
	@ManyToOne
	private Location location;
	
}
