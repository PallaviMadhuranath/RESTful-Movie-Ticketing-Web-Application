package com.pallavi.movieticket.http;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.resteasy.annotations.providers.jaxb.Wrapped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pallavi.movieticket.entity.Theater;
import com.pallavi.movieticket.entity.impl.TheaterImpl;
import com.pallavi.movieticket.http.entity.HttpMovie;
import com.pallavi.movieticket.http.entity.HttpTheater;
import com.pallavi.movieticket.service.TheaterService;
import com.pallavi.movieticket.service.exception.MovieTicketException;

//specifies a relative path for the resource
@Path("/theaters")
@Component
// while sending a request client-agent tells server how it is sending the
// request. Here it is in JSON format
@Consumes({ MediaType.APPLICATION_JSON })
// server tells the client-agent how it is responding to the request. Here it is
// JSON
@Produces({ MediaType.APPLICATION_JSON })
public class TheaterResource {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private TheaterService theaterService;

	@POST
	@Path("/")
	public Response createMovie(HttpTheater newTheater) {
		Theater theaterCreate = convert(newTheater);
		Theater addedTheater = theaterService.addTheater(theaterCreate);
		return Response.status(Status.CREATED).header("Location", "/theaters/" + addedTheater.getID())
				.entity(new HttpTheater(addedTheater)).build();
	}

	@GET
	@Path("/{theaterId}")
	@Wrapped(element = "theaters")
	public HttpTheater getTheaterByID(@PathParam("theaterId") long id) {
		logger.info("Theater id:", id);

		try {
			Theater theater = theaterService.getTheaterByID(id);
			return new HttpTheater(theater);
		} catch (Exception e) {
			System.out.println("Theater Id " + id + " not found");
		}
		return null;

	}

	@GET
	@Path("/")
	@Wrapped(element = "theaters")
	public List<HttpTheater> getTheaterSearch(@QueryParam("theaterName") String theaterName)
			throws MovieTicketException {
		logger.info("Movie search names=" + theaterName);
		List<Theater> theaterFound = theaterService.getTheatersByName(theaterName);
		List<HttpTheater> returnList = new ArrayList<>(theaterFound.size());
		for (Theater theater : theaterFound) {
			returnList.add(new HttpTheater(theater));
		}
		return returnList;

	}

	private Theater convert(HttpTheater httpTheater) {
		TheaterImpl theater = new TheaterImpl();
		theater.setName(httpTheater.name);
		theater.setCity(httpTheater.city);
		theater.setZipCode(httpTheater.zipCode);
		return theater;
	}

}
