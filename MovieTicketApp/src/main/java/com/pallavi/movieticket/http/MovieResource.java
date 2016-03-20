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

import com.pallavi.movieticket.entity.Movie;
import com.pallavi.movieticket.http.entity.HttpMovie;
import com.pallavi.movieticket.service.MovieService;
import com.pallavi.movieticket.service.exception.MovieTicketException;

//specifies a relative path for the resource
@Path("/movies")
@Component
// while sending a request client-agent tells server how it is sending the
// request. Here it is in JSON format
@Consumes({ MediaType.APPLICATION_JSON })
// server tells the client-agent how it is responding to the request. Here it is
// JSON
@Produces({ MediaType.APPLICATION_JSON })
public class MovieResource {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private MovieService movieService;

	@GET
	@Path("/movieName")
	@Wrapped(element = "movies")
	public HttpMovie getMovieByName(@QueryParam("title") String name) throws MovieTicketException {
		logger.info("movie name:", name);
		Movie movie = movieService.getMovieByName(name);
		return new HttpMovie(movie);

	}

	@GET
	@Path("/")
	@Wrapped(element = "movies")
	public List<HttpMovie> getAllMovies() throws MovieTicketException {
		logger.info("All movie names");
		List<Movie> movieFound = movieService.getAllMovies();
		List<HttpMovie> returnList = new ArrayList<>(movieFound.size());
		for (Movie movie : movieFound) {
			returnList.add(new HttpMovie(movie));
		}
		return returnList;

	}

}
