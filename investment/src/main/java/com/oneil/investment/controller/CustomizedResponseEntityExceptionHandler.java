package com.oneil.investment.controller;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.oneil.investment.exception.ExceptionResponse;
import com.oneil.investment.exception.FundNotFoundException;
import com.oneil.investment.exception.ClientNotFoundException;

import org.springframework.web.bind.MethodArgumentNotValidException;


@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler 
extends ResponseEntityExceptionHandler {
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		
		return new ResponseEntity<>(new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false)), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ClientNotFoundException.class)
	public final ResponseEntity<Object> handleclientNotFoundException(ClientNotFoundException ex, WebRequest request) {
		return new ResponseEntity<>(new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false)), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(FundNotFoundException.class)
	public final ResponseEntity<Object> handlefundNotFoundException(FundNotFoundException ex, WebRequest request) {
		
		return new ResponseEntity<>(new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false)), HttpStatus.NOT_FOUND);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		return new ResponseEntity<>(new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false)), HttpStatus.BAD_REQUEST);
		
	}	
}
