package io.jpe.starter.interceptor;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.jpe.starter.ThcApiApp;




@RestController
public class ExecutionTimeControl {
	
	private static final Logger logger = LogManager.getLogger(ExecutionTimeControl.class);
	
	@Autowired
	private ExecutionTimeService executionTimeService;
	
	@GetMapping("/all-execution-times")
	public List<ExecutionTimeRecord> getLocations() {
		logger.info("getting all execution time records");
		List<ExecutionTimeRecord> list = null;
		try {
			list = executionTimeService.getAllExecutionTimes();
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		return list;
		
	}
	
	@GetMapping("/execution-times")
	public List<ExecutionTimeRecord> getLocationsWithPaginationDefault() {
		List<ExecutionTimeRecord> list = null; 
		logger.info("getting first page of execution time records");
		try {
		list = executionTimeService.getAllExecutionTimesPagination(0);
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		return list;
	}
	
	@GetMapping("/execution-times/page/{num}")
	public List<ExecutionTimeRecord> getLocationsWithPagination(@PathVariable int num) {
		List<ExecutionTimeRecord> list = null; 
		try {
			list = executionTimeService.getAllExecutionTimesPagination(num-1);
		} catch (Exception e) {
			
			logger.error(e.getMessage());
			logger.error(e.getStackTrace().toString());
		}
		return list;
	}
	
}
