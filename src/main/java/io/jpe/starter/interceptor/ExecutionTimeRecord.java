package io.jpe.starter.interceptor;

import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;


@Setter @Getter
@Entity
public class ExecutionTimeRecord {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String requesturl;
	private Long executiontime_in_ms;
	private Date date;
	
	ExecutionTimeRecord(){}
	
	ExecutionTimeRecord(String requrl, Long exectime, Date reqDate){
		this.requesturl = requrl;
		this.executiontime_in_ms = exectime;
		this.date = reqDate;
	}
}
