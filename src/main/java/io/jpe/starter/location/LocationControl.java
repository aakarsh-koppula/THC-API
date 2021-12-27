package io.jpe.starter.location;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class LocationControl {
	private static final Logger logger = LogManager.getLogger(LocationControl.class);
	@Autowired
	private LocationService locationservice;
	
	@GetMapping("/all-locations")
	public List<Location> getLocations() {
		logger.info("getting all locations");
		List<Location> listLocations = null;
		try {
			listLocations = locationservice.getAllLocations();
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error(e.getStackTrace().toString());
		}
		return listLocations;
	}
	
	@GetMapping("/locations/page/{num}")
	public List<Location> getLocationsWithPagination(@PathVariable int num) {
		List<Location> listLocations = null;
		logger.info("getting results of locations with pagination");
		try {
			listLocations = locationservice.getLocationsWithPagination(num-1);
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error(e.getStackTrace().toString());
		}
		return listLocations;
	}
	
	@GetMapping("/locations")
	public List<Location> getLocationsWithPaginationDefault() {
		List<Location> listLocations = null;
		logger.info("getting first page results of locations");
		try {
			listLocations = locationservice.getLocationsWithPagination(0);
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error(e.getStackTrace().toString());
		}
		return listLocations;
	}
	
	@GetMapping("/locations/{id}")
	public Optional<Location> getSpecificLocation(@PathVariable int id) {
		Optional<Location> optionalLocation = null;
		logger.info("getting one Location as per id");
		try {
			optionalLocation = locationservice.getLocationById(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error(e.getStackTrace().toString());
		}
		return optionalLocation;
	}
	
	@GetMapping("/")
	public String baseurl() {
		return "Welcome";
	}
	
	@PostMapping("/locations/add-a-location")
	public void addLocation(@RequestBody Location loc ) {
		logger.info("adding a location");
		try {
			locationservice.addLocation(loc);
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error(e.getStackTrace().toString());
		}
	}
	
	@PostMapping("/locations/add-multiple-location")
	public void addManyLocations(@RequestBody List<Location> locationlist ) {
		logger.info("adding multiple location");
		try {
			for (Location loc: locationlist) {
				locationservice.addLocation(loc);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error(e.getStackTrace().toString());
		}
		
	}
	
	@PutMapping("/locations/update-location/{id}")
	public void updateLocation(@RequestBody Location loc, @PathVariable int id ) {
		logger.info("adding multiple locations");
		try {
			locationservice.addLocation(loc);
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error(e.getStackTrace().toString());
		}
	}

	@DeleteMapping("/locations/delete-location/{id}")
	public void deleteLocation( @PathVariable int id ) {
		logger.info("deleting a location");
		try {
			locationservice.deleteLocation(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error(e.getStackTrace().toString());
		}
	}

}
