package com.pallavi.movieticket.http.exception;

import com.pallavi.movieticket.service.exception.ErrorCode;
import com.pallavi.movieticket.service.exception.MovieTicketException;

@SuppressWarnings("serial")
public class InvalidFieldException extends MovieTicketException {
	
	public InvalidFieldException(String message, Throwable throwable) {
		super(ErrorCode.INVALID_FIELD, message, throwable);
	}
	
	public InvalidFieldException(String message) {
		super(ErrorCode.INVALID_FIELD, message);
	}

}
