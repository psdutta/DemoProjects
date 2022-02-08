package com.task2.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AddressExceptionController {

	@ExceptionHandler(value = AddressNotfoundException.class)
	public ResponseEntity<Object> exception(AddressNotfoundException exception) {
	   return new ResponseEntity<>("Address not found", HttpStatus.NOT_FOUND);
	}
}
