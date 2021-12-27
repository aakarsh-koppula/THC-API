package io.jpe.starter.menuitem;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import io.jpe.starter.location.Location;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class MenuItem {
	@Id
	private int itemid;
	private String name;
	private int price;
	
	@ManyToOne
	private Location location;
}
