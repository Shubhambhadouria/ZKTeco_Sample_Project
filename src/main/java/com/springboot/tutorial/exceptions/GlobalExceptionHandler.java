package com.springboot.tutorial.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> resourceNotFoundException(ResourceNotFoundException rnf,WebRequest req){
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setTimestamp(LocalDateTime.now());
		errorDetails.setMessage(rnf.getMessage());
		errorDetails.setDetails(req.getDescription(false));
		return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.OK);
	}
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> otherException(Exception e,WebRequest req){
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setTimestamp(LocalDateTime.now());
		errorDetails.setMessage(e.getMessage());
		errorDetails.setDetails(req.getDescription(false));
		return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.OK);
	}
}
