package com.task1.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AddressExceptionController {

	@ExceptionHandler(value = UserNotfoundException.class)
	public ResponseEntity<Object> exception(UserNotfoundException exception) {
	   return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
	}
}
