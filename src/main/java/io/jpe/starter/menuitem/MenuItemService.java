package io.jpe.starter.menuitem;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import io.jpe.starter.location.Location;
import io.jpe.starter.location.LocationRepository;


@Service
public class MenuItemService {
	private static final Logger logger = LogManager.getLogger(MenuItemService.class);
	@Autowired
	private MenuItemRepository menuItemRepository;
	
	public void addMenuItemToLocation(MenuItem item) {
		logger.info("saving a menuitem object");
		
		try {
			menuItemRepository.save(item);
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error(e.getStackTrace().toString());
		}
	}

	public List<MenuItem> getAllMenuItemsFromLocation(int id) {
		logger.info("function to retrieve all menuitem objects");
		List<MenuItem> menulist = new ArrayList<>();
		try {
			menuItemRepository.findAllByLocationId(id).forEach(menulist::add);
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error(e.getStackTrace().toString());
		}
		return menulist;
	}
	
	public void deleteMenuItemFromLocation(MenuItem item) {
		logger.info("deleting a menuitem object");
		try {
			menuItemRepository.delete(item);
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error(e.getStackTrace().toString());
		}
	}
	
	public List<MenuItem> getMeniItemsWithPagination(int id, int offset){
		logger.info("function to retrieve all menuitem objects page wise");
        List<MenuItem> menuitems = null;
		try {
			menuitems = menuItemRepository.findAllByLocationId(id, PageRequest.of(offset, 3));
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error(e.getStackTrace().toString());
		}
        return  menuitems;
	}

	
//	public List<Location> getAllLocations() {
//		List<Location> locationsList = new ArrayList<>();
//		locationRepository.findAll().forEach(locationsList::add);
//		return locationsList;
//	}
	
}
