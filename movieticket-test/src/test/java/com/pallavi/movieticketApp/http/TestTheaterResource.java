package com.pallavi.movieticketApp.http;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestTheaterResource {
	
	private static final String HTTP_HOST = "http://localhost:8080";
	private static final String URI_PATH = "MovieTicketApp/rest/theaters";

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
	
	private void verifyMissing(Response response) {
		HttpError error = response.readEntity(HttpError.class);
		Assert.assertEquals(409, response.getStatus());
		Assert.assertEquals(409, error.status);
		Assert.assertEquals("MISSING_DATA", error.code);
		Assert.assertEquals("no search parameter provided", error.message);
		Assert.assertEquals("", error.debug);

	}
	
	@Test
	public void testGetMovieParamsJson() {

		Response response = target.queryParam("theaterName", "Century").request().accept(MediaType.APPLICATION_JSON)
				.get();
		checkStatus(response);
	}
	
	private void checkStatus(Response response) {
		List<HttpTheater> searchResponse = response.readEntity(new GenericType<List<HttpTheater>>() {
		});

		Assert.assertEquals(200, response.getStatus());
		Assert.assertEquals(1, searchResponse.size());
		Assert.assertEquals("94035", searchResponse.get(0).zipCode);
		//Assert.assertNotEquals("French", searchResponse.get(0).language);
	}

}
