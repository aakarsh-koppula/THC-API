package io.jpe.starter.menuitem;

import java.util.ArrayList;
import java.util.List;

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

import io.jpe.starter.ThcApiApp;
import io.jpe.starter.location.Location;


@RestController
public class MenuItemControl {
	
	private static final Logger logger = LogManager.getLogger(MenuItemControl.class);
	
	@Autowired
	private MenuItemService menuItemService;
	
	@GetMapping("/location/{id}/all-menu-items")
	public List<MenuItem> allMenuItemsFromALocation(@PathVariable int id) {
		logger.info("retrieving all menu items from location ");
		List<MenuItem> item = null;
		try {
			item = menuItemService.getAllMenuItemsFromLocation(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error(e.getStackTrace().toString());
		}	
		return item;
	}
	
	@GetMapping("/location/{id}/menu-items")
	public List<MenuItem> getMeniItemsWithPaginationDefault(@PathVariable int id) {
		logger.info("retrieving first page of menu items from location ");
		List<MenuItem> itemList = null;
		
		try {
			itemList = menuItemService.getMeniItemsWithPagination(id,0);
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error(e.getStackTrace().toString());
		}
		return itemList;
	}
	
	@GetMapping("/location/{id}/menu-items/page/{num}")
	public List<MenuItem> getMeniItemsWithPagination(@PathVariable int id, @PathVariable int num) {
		logger.info("retrieving menu items pagewise from location ");
		List<MenuItem> itemList = null;
		try {
			itemList = menuItemService.getMeniItemsWithPagination(id,num-1);
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error(e.getStackTrace().toString());
		}
		return itemList;
	}
	
	
	@GetMapping("/location/{id}/menu-item/{itemid}")
	public MenuItem menuItemFromALocation(@PathVariable int id, @PathVariable int itemid) {
		logger.info("retrieving one menuitem");
		List<MenuItem> items = new ArrayList<>();
		try {
			items = menuItemService.getAllMenuItemsFromLocation(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error(e.getStackTrace().toString());
		}
		for (MenuItem item : items) {
			if (item.getItemid() == itemid)
				return item;
		}
		return null;
	}
	
	@PostMapping("/location/{id}/add-to-menu")
	public void addMenuItemToALocation(@RequestBody MenuItem item, @PathVariable int id ) {
		logger.info("Adding a menu item to a location");
		item.setLocation(new Location(id));
		try {
			menuItemService.addMenuItemToLocation(item);
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error(e.getStackTrace().toString());
		}
	}
	
	@PostMapping("/location/{id}/add-multiple-items-to-menu")
	public void addMenuItemsToALocation(@RequestBody List<MenuItem> items, @PathVariable int id ) {
		logger.info("Adding multiple menu items to a location");
		for(MenuItem item: items) {
			item.setLocation(new Location(id));
			try {
				menuItemService.addMenuItemToLocation(item);
			} catch (Exception e) {
				logger.error(e.getMessage());
				logger.error(e.getStackTrace().toString());
			}
		}
	}
	
	@PutMapping("/location/{id}/update-menu-item")
	public void updateMenuItemAtALocation(@RequestBody MenuItem item, @PathVariable int id ) {
		logger.info("updating a menu item at a location");
		item.setLocation(new Location(id));
		try {
			menuItemService.addMenuItemToLocation(item);
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error(e.getStackTrace().toString());
		}
	}
	
	@PutMapping("/location/{id}/update-multiple-items-in-menu")
	public void updateMenuItemsAtALocation(@RequestBody List<MenuItem> items, @PathVariable int id ) {
		logger.info("updating multiple menu item at a location");
		for(MenuItem item: items) {
			item.setLocation(new Location(id));
			try {
				menuItemService.addMenuItemToLocation(item);
			} catch (Exception e) {
				logger.error(e.getMessage());
				logger.error(e.getStackTrace().toString());
			}
		}
	}
	
	@DeleteMapping("/location/{id}/menu-item/{itemid}")
	public void deleteMenuItemFromALocation(@PathVariable int id, @PathVariable int itemid) {
		logger.info("deleting a menu item at a location");
		List<MenuItem> items = new ArrayList<>();
		items = menuItemService.getAllMenuItemsFromLocation(id);
		for (MenuItem item : items) {
			if (item.getItemid() == itemid)
				try {
					menuItemService.deleteMenuItemFromLocation(item);
				} catch (Exception e) {
					logger.error(e.getMessage());
					logger.error(e.getStackTrace().toString());
				}	
		}
	}
	
}
