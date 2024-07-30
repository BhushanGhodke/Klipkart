package com.klipkart.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionController {

	
	
	
	
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<AppExceptionInfo> handleAppException( Exception e ){
		
	  AppExceptionInfo info = new AppExceptionInfo();
	  
	  info.setExCode("KKEXP007");
	  info.setExMsg(e.getMessage());
	  info.setExDate(LocalDateTime.now());
	  
	  return new ResponseEntity<AppExceptionInfo>(info, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	
	@ExceptionHandler(value = ProductNotFoundException.class)
	public ResponseEntity<AppExceptionInfo> handleProductNotFoundEx( ProductNotFoundException e ){
		
	  AppExceptionInfo info = new AppExceptionInfo();
	  
	  info.setExCode("KKEX88");
	  info.setExMsg(e.getMessage());
	  info.setExDate(LocalDateTime.now());
	  
	  return new ResponseEntity<AppExceptionInfo>(info, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
 
}
