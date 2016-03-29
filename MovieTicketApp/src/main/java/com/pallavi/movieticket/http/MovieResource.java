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

import com.pallavi.movieticket.entity.Movie;
import com.pallavi.movieticket.entity.impl.MovieImpl;
import com.pallavi.movieticket.http.entity.HttpMovie;
import com.pallavi.movieticket.service.MovieService;
import com.pallavi.movieticket.service.exception.MovieTicketException;

//specifies a relative path for the resource
@Path("/movies")
@Component
// while sending a request client-agent tells server how it is sending the
// request. Here it is in JSON format
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
// server tells the client-agent how it is responding to the request. Here it is
// JSON
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
public class MovieResource {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private MovieService movieService;

	@POST
	@Path("/")
	public Response createMovie(HttpMovie newMovie) {
		Movie movieCreate = convert(newMovie);
		Movie addedMovie = movieService.addMovie(movieCreate);
		return Response.status(Status.CREATED).header("Location", "/movies/" + addedMovie.getID())
				.entity(new HttpMovie(addedMovie)).build();
	}

	@GET
	@Path("/{movieId}")
	@Wrapped(element = "movies")
	public HttpMovie getMovieByName(@PathParam("movieId") long id) {
		logger.info("movie id:", id);
		try {
			Movie movie = movieService.getMovieById(id);
			return new HttpMovie(movie);
		} catch (Exception e) {
			System.out.println("Movie Id " + id + " not found");
		}
		return null;
	}

	@GET
	@Path("/")
	@Wrapped(element = "movies")
	public List<HttpMovie> getMoviesSearch(@QueryParam("movieName") String movieName) throws MovieTicketException {
		System.out.println("ENteres");
		logger.info("Movie search names=" + movieName);
		List<Movie> movieFound = movieService.getMoviesByName(movieName);
		List<HttpMovie> returnList = new ArrayList<>(movieFound.size());
		for (Movie movie : movieFound) {
			returnList.add(new HttpMovie(movie));
		}
		return returnList;

	}

	private Movie convert(HttpMovie httpMovie) {
		MovieImpl movie = new MovieImpl();
		movie.setName(httpMovie.name);
		movie.setLanguage(httpMovie.language);
		movie.setGenre(httpMovie.genre);
		return movie;
	}

	@GET
	@Path("/{theaterName}")
	@Wrapped(element = "movies")
	public List<HttpMovie> getMovieByTheater(@PathParam("theaterName") String name) throws MovieTicketException {
		logger.info("theater names");

		List<Movie> movieFound = movieService.getMovieByTheater(name);
		List<HttpMovie> returnList = new ArrayList<>(movieFound.size());
		for (Movie movie : movieFound) {
			returnList.add(new HttpMovie(movie));
		}
		return returnList;
	}

}
