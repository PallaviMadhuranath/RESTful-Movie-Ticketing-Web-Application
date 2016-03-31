package com.pallavi.movieticket.http.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;

import com.pallavi.movieticket.service.exception.ErrorCode;
import com.pallavi.movieticket.service.exception.InvalidFieldException;
import com.pallavi.movieticket.service.exception.MovieTicketException;

@Provider
@Component
public class InvalidFieldExceptionMapper implements ExceptionMapper<InvalidFieldException> {

	@Override
	public Response toResponse(InvalidFieldException exception) {
		return Response.status(Status.CONFLICT).entity(new HttpError(exception)).build();
	}

}
