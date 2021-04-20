package com.nagarro.hr.manager.api.controller;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.nagarro.hr.manager.api.exception.MyException;
import com.nagarro.hr.manager.api.model.ApiError;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(MyException.class)
	protected ResponseEntity<Object> customExceptionHandler(MyException ex) {
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage());
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> allExceptionHandler(Exception ex) {
		ex.printStackTrace();
		ApiError apiError = new ApiError();
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

}