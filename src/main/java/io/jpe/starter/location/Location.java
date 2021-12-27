package io.jpe.starter.location;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
@Entity
public class Location {
	@Id
	private int id;
	private String address;
	private String city;
	private String zip;
	private String weekday_openhours;
	private String weekend_openhours;
	
	Location(){}
	
	public Location(int locid){
		this.id = locid;
		this.city = this.address = this.zip 
		= this.weekday_openhours = this.weekend_openhours = "";
	}
}
