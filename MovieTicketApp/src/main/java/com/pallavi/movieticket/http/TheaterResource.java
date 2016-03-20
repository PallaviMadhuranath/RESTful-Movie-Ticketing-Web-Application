package com.pallavi.movieticket.http;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.providers.jaxb.Wrapped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pallavi.movieticket.entity.Theater;
import com.pallavi.movieticket.http.entity.HttpTheater;
import com.pallavi.movieticket.service.TheaterService;
import com.pallavi.movieticket.service.exception.MovieTicketException;

//specifies a relative path for the resource
@Path("/theaters")
@Component
//while sending a request client-agent tells server how it is sending the
//request. Here it is in JSON format
@Consumes({ MediaType.APPLICATION_JSON })
//server tells the client-agent how it is responding to the request. Here it is
//JSON
@Produces({ MediaType.APPLICATION_JSON })
public class TheaterResource {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private TheaterService theaterService;

	@GET
	@Path("/theaterName")
	@Wrapped(element = "theaters")
	public HttpTheater getMovieByName(@QueryParam("name") String name) throws MovieTicketException {
		logger.info("Theater name:", name);
		Theater theater = theaterService.getTheaterByName(name);
		return new HttpTheater(theater);

	}

	@GET
	@Path("/")
	@Wrapped(element = "theaters")
	public List<HttpTheater> getAllTheaters() throws MovieTicketException {
		logger.info("All theater names");
		List<Theater> theaterFound = theaterService.getAllTheaters();
		List<HttpTheater> returnList = new ArrayList<>(theaterFound.size());
		for (Theater theater : theaterFound) {
			returnList.add(new HttpTheater(theater));
		}
		return returnList;

	}


}
