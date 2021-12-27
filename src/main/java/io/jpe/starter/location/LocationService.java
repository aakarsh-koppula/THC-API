package io.jpe.starter.location;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class LocationService {
	private static final Logger logger = LogManager.getLogger(LocationService.class);
	@Autowired
	private LocationRepository locationRepository;
	
	public void addLocation(Location loc) {
		logger.info("saving a location record");
		
		try {
			locationRepository.save(loc);
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error(e.getStackTrace().toString());
		}
	}
	
	
	public List<Location> getAllLocations() {
		logger.info("retrieving  all location record");
		List<Location> locationsList = new ArrayList<>();
		try {
			locationRepository.findAll().forEach(locationsList::add);
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error(e.getStackTrace().toString());
		}
		return locationsList;
	}
	
	
	public List<Location> getLocationsWithPagination(int offset){
		logger.info("retrieving  a page of location record");
	        Page<Location> locations = null;
			try {
				locations = locationRepository.findAll(PageRequest.of(offset, 3));
			} catch (Exception e) {
				logger.error(e.getMessage());
				logger.error(e.getStackTrace().toString());
			}
	        return  locations.getContent();
	}
	
	

	public void deleteLocation(int id) {
		logger.info("deleting  a location record");
		try {
			locationRepository.deleteById(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error(e.getStackTrace().toString());
		}	
	}

	
	public Optional<Location> getLocationById(int id) {	
		logger.info("retrieving  a location record");
		Optional<Location> loc = null;
		try {
			loc = locationRepository.findById(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error(e.getStackTrace().toString());
		}
		return loc;
	}

}
