package com.pallavi.movieticketApp.http;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class TestMovieResource {

	private static final String HTTP_HOST = "http://localhost:8080";
	private static final String URI_PATH = "MovieTicketApp/rest/movies";

	private Client client = ClientBuilder.newClient();
	private WebTarget target;

	@Before
	public void init() {
		target = client.target(HTTP_HOST).path(URI_PATH);
	}

	@Test
	public void testGetMoviesNoParamsJson() {
		Response response = target.request().accept(MediaType.APPLICATION_JSON).get();
		verifyMissing(response);

	}

	@Test
	public void testGetMovieInvalidParamsJson() {
		Response response = target.queryParam("movieName", "foo").request().accept(MediaType.APPLICATION_JSON).get();
		verifyNotFound(response);
	}

	private void verifyMissing(Response response) {
		HttpError error = response.readEntity(HttpError.class);
		Assert.assertEquals(409, response.getStatus());
		Assert.assertEquals(409, error.status);
		Assert.assertEquals("MISSING_DATA", error.code);
		Assert.assertEquals("no search parameter provided", error.message);
		Assert.assertEquals("", error.debug);

	}

	private void verifyNotFound(Response response) {
		List<HttpMovie> searchResponse = response.readEntity(new GenericType<List<HttpMovie>>() {
		});
		Assert.assertEquals(0, searchResponse.size());
	}

	/**
	 * Testing for query parameters.
	 */
	@Test
	public void testGetMovieParamsJson() {

		Response response = target.queryParam("movieName", "Titanic").request().accept(MediaType.APPLICATION_JSON)
				.get();
		checkStatus(response);
	}

	/**
	 * Check for status of 200.
	 * 
	 * @param response
	 *            - has the response from the server in Json form.
	 */
	private void checkStatus(Response response) {
		List<HttpMovie> searchResponse = response.readEntity(new GenericType<List<HttpMovie>>() {
		});

		Assert.assertEquals(200, response.getStatus());
		Assert.assertEquals(2, searchResponse.size());
		Assert.assertEquals("English", searchResponse.get(0).language);
		Assert.assertEquals("Hindi", searchResponse.get(1).language);
		Assert.assertNotEquals("French", searchResponse.get(0).language);
	}

}
