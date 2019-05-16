package com.exception;

import java.util.Date;

import lombok.Data;

@Data
public class ExceptionResponse {
	
	String message;
	
	Date timestamp;
	
	String details;

	public ExceptionResponse(String message, Date timestamp, String details) {
		super();
		this.message = message;
		this.timestamp = timestamp;
		this.details = details;
	}
	
}
