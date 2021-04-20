package com.nagarro.hr.manager.api.exception;

public class MyException extends Exception {

	private static final long serialVersionUID = 5613473066748709093L;

	public MyException(String message) {
		super(message);
	}

	public MyException(String message, Throwable cause) {
		super(message, cause);
	}

}
