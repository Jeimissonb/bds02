package com.devsuperior.bds02.controllers.exception.intercept;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devsuperior.bds02.services.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class CityControllerAdvice {

	@ExceptionHandler
	ResponseEntity<StandardError> cityNotFound (ResourceNotFoundException e, HttpServletRequest http) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError errors = new StandardError();
		errors.setTimestamp(Instant.now());
		errors.setStatus(status.value());
		errors.setMessage(e.getMessage());
		errors.setError("City not found :\"");
		errors.setPath(http.getRequestURI());
		return ResponseEntity.status(status).body(errors);
	}
	
	
	//tentei uma abordagem diferente aqui, passando a exception diretão, pra ver o stacktrace na message"
	@ExceptionHandler
	ResponseEntity<StandardError> databaseIntegrity (DataIntegrityViolationException e, HttpServletRequest http) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError errors = new StandardError();
		errors.setTimestamp(Instant.now());
		errors.setStatus(status.value());
		errors.setMessage(e.getMessage());
		errors.setError("Database integrity exception :\"");
		errors.setPath(http.getRequestURI());
		return ResponseEntity.status(status).body(errors);
	}
}
