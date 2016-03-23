package com.pallavi.movieticket.http;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
	@Path("/{movieId}")	
	@Wrapped(element = "movies")
	public HttpMovie getMovieByName(@PathParam("movieId") String id) {
		logger.info("movie id:", id);
		try{
			Movie movie = movieService.getMovieById(id);
			return new HttpMovie(movie);
		}
		catch(Exception e) {
			System.out.println("Movie Id " + id + " not found");
		}
		return null;
	}

	@GET
	@Path("/")
	@Wrapped(element = "movies")
	public List<HttpMovie> getMoviesSearch(@QueryParam("movieName") String movieName) throws MovieTicketException {
		System.out.println("ENteres");
		logger.info("Movie search names="+movieName);
		List<Movie> movieFound = movieService.getMoviesByName(movieName);
		List<HttpMovie> returnList = new ArrayList<>(movieFound.size());
		for (Movie movie : movieFound) {
			returnList.add(new HttpMovie(movie));
		}
		return returnList;

	}
	
	/*@GET
	@Wrapped(element = "movies")
	public List<HttpMovie> getTheaterByMovie(@QueryParam("name") String name){
		logger.info("theater names");
		
		List<Movie> movieFound = movieService.getMovieByTheater(name);
		List<HttpMovie> returnList = new ArrayList<>(movieFound.size());
		for (Movie movie : movieFound) {
			returnList.add(new HttpMovie(movie));
		}
		return returnList;
	} */

}
