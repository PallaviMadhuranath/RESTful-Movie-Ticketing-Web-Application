package com.pallavi.movieticket.http;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.hibernate.Hibernate;
import org.jboss.resteasy.annotations.providers.jaxb.Wrapped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.pallavi.movieticket.entity.Showtime;
import com.pallavi.movieticket.entity.Theater;
import com.pallavi.movieticket.entity.impl.TheaterImpl;
import com.pallavi.movieticket.http.entity.HttpShowtime;
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
	public Response getTheaterByID(@PathParam("theaterId") long id) {
		logger.info("Theater id:", id);
		Theater theater = theaterService.getTheaterByID(id);

		if (theater == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("Theater not found for theaterId: " + id).build();
		}
		//initializing for the fetch to happen. Since it is a lazy fetch.
		Hibernate.initialize(theater);
		Hibernate.initialize(theater.getShowtime());
		HttpTheater httpTheater = new HttpTheater(theater);

		Iterator<Showtime> iterator = theater.getShowtime().iterator();
		while (iterator.hasNext()) {
			Showtime showtime = iterator.next();
			httpTheater.getShowtimes().add(new HttpShowtime(showtime));
		}
		return Response.ok(httpTheater).build();
	}

	@GET
	@Path("/")
	@Wrapped(element = "theaters")
	public Response getTheaterSearch(@QueryParam("theaterName") String theaterName,
			@QueryParam("movieName") String movieName) throws MovieTicketException {
		logger.info("Movie search names=" + theaterName);
		List<Theater> theaterFound = new ArrayList<Theater>();

		if (movieName != null && !movieName.trim().equals("")) {
			theaterFound = theaterService.getTheaterByMovie(movieName);
		} else if (theaterName != null && !theaterName.trim().equals("")) {
			theaterFound = theaterService.getTheatersByName(theaterName);
		} else {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("Exepting movieName or theaterName as query param").build();
		}
		List<HttpTheater> returnList = new ArrayList<>(theaterFound.size());
		for (Theater theater : theaterFound) {
			returnList.add(new HttpTheater(theater));
		}
		String jsonResponse = new Gson().toJson(returnList);
		return Response.ok(jsonResponse).build();

	}

	@DELETE
	@Path("/{theaterId}")
	@Wrapped(element = "theaters")
	public Response deleteMovie(@PathParam("theaterId") long id) {
		Theater theater = theaterService.getTheaterByID(id);
		if (theater == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("Theater not found for movieId: " + id).build();
		}

		// deleting the movie
		theaterService.deleteTheater(id);
		;

		return Response.ok(new HttpTheater(theater)).build();
	}

	private Theater convert(HttpTheater httpTheater) {
		TheaterImpl theater = new TheaterImpl();
		theater.setName(httpTheater.name);
		theater.setCity(httpTheater.city);
		theater.setZipCode(httpTheater.zipCode);
		return theater;
	}

}
