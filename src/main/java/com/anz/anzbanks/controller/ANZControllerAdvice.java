package com.anz.anzbanks.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.anz.anzbanks.exception.AccountException;

@RestControllerAdvice
public class ANZControllerAdvice extends ResponseEntityExceptionHandler{

	@Autowired
	private Environment environment;
	
	@ExceptionHandler(value = {AccountException.class})
	public ResponseEntity<String> handleAccountException(AccountException accountException) {

		
		ResponseEntity<String> response = null;
		String exceptionMessage = accountException.getMessage();
		if("INVALID_ACCOUNT_NO".equalsIgnoreCase(exceptionMessage)) {
			response = new ResponseEntity<String>(environment.getProperty("INVALID_ACCOUNT_NO"),HttpStatus.BAD_REQUEST);
		}
		else if("NO_SUCH_ACCOUNT".equalsIgnoreCase(exceptionMessage)) {
			response = new ResponseEntity<String>(environment.getProperty("NO_SUCH_ACCOUNT"),HttpStatus.NOT_FOUND);
		}
		else {
			response = new ResponseEntity<String>(exceptionMessage,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
}
