package com.javaweb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = { NumberFormatException.class })
	public ResponseEntity<?> handleNumberFormatException(NumberFormatException ex) {
		Map<String, Object> error = new LinkedHashMap<>();
		error.put("status", HttpStatus.BAD_REQUEST);
		error.put("error", ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}
