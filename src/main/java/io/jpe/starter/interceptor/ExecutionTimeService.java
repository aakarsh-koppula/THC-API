package io.jpe.starter.interceptor;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import io.jpe.starter.ThcApiApp;

@Service
public class ExecutionTimeService {
	private static final Logger logger = LogManager.getLogger(ExecutionTimeService.class);
	@Autowired
	private ExecutionTimeRepository exectionTimeRepository;
	
	public List<ExecutionTimeRecord> getAllExecutionTimes(){
		List<ExecutionTimeRecord> executionTimes = new ArrayList<>();
		
		try {
			exectionTimeRepository.findAll().forEach(executionTimes::add);
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error(e.getStackTrace().toString());
			
		}
		
		return executionTimes;
	}
	
	public List<ExecutionTimeRecord> getAllExecutionTimesPagination(int offset){
		Page<ExecutionTimeRecord> executionTimes = null;
		try {
			executionTimes = exectionTimeRepository.findAll(PageRequest.of(offset, 5));
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error(e.getStackTrace().toString());
		}
		
		return executionTimes.getContent();
	}

}
