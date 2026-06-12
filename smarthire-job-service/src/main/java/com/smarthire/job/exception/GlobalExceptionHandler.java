package com.smarthire.job.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

	 @ExceptionHandler(JobNotFoundException.class)
	    public ResponseEntity<?> handleJobNotFound(JobNotFoundException ex) {

	        Map<String, Object> error = new HashMap<>();

	        error.put("timestamp", LocalDateTime.now());
	        error.put("message", ex.getMessage());
	        error.put("status", 404);

	        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	    }

	    @ExceptionHandler(UnauthorizedException.class)
	    public ResponseEntity<?> handleUnauthorized(UnauthorizedException ex) {

	        Map<String, Object> error = new HashMap<>();

	        error.put("timestamp", LocalDateTime.now());
	        error.put("message", ex.getMessage());
	        error.put("status", 403);

	        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
	    }

	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<?> handleGeneral(Exception ex) {

	        Map<String, Object> error = new HashMap<>();

	        error.put("timestamp", LocalDateTime.now());
	        error.put("message", ex.getMessage());
	        error.put("status", 500);

	        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
}
