package io.jpe.starter.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import io.jpe.starter.ThcApiApp;
@Component
public class ExecutionTimeInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private ExecutionTimeRepository executionTimeRepository;
	private static final String START_TIME_ATTR_NAME = "startTime";
    private static final String EXECUTION_TIME_ATTR_NAME = "executionTime";
    private static final Logger logger = LogManager.getLogger(ExecutionTimeInterceptor.class);
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	logger.info("preHandle for api running");
        long startTime = System.currentTimeMillis();
        request.setAttribute(START_TIME_ATTR_NAME, startTime);
        logger.info("preHandle for api completed");
        return true;
    }
 
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long startTime = (Long) request.getAttribute(START_TIME_ATTR_NAME);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        
        logger.info("postHandle for"+ ((HttpServletRequest)request).getRequestURI() + "running");
        ExecutionTimeRecord rec = new ExecutionTimeRecord(((HttpServletRequest)request).getRequestURI(), executionTime, new Date());
        executionTimeRepository.save(rec);
        System.out.println(((HttpServletRequest)request).getRequestURI() +"  "+executionTime);
        logger.info("postHandle for"+ ((HttpServletRequest)request).getRequestURI() + "completed");
    }

}
