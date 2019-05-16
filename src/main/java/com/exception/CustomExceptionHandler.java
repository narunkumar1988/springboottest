package com.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{
	
	/*
	 * @ExceptionHandler(ProductNotFoundException.class)
	 * 
	 * @ResponseStatus(HttpStatus.NOT_FOUND) public
	 * ResponseEntity<ExceptionResponse> handleProductNotException(Exception ex,
	 * WebRequest request) throws Exception { ExceptionResponse response = new
	 * ExceptionResponse(ex.getMessage(), new Date(),
	 * request.getDescription(false)); return new
	 * ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND); }
	 */
	
	@ExceptionHandler(ProductNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ExceptionResponse handleProductNotException(Exception ex, WebRequest request) throws Exception {
		return new ExceptionResponse(ex.getMessage(), new Date(), request.getDescription(false));
	}


}
