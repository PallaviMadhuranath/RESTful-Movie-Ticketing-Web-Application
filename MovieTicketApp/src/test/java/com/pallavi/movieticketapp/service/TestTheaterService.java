package com.pallavi.movieticketapp.service;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pallavi.movieticket.service.impl.TheaterServiceImpl;

import org.junit.Assert;

public class TestTheaterService {

	ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring-context.xml"});
	@Test
	public void testGetAllTheaters(){
		TheaterServiceImpl ts = (TheaterServiceImpl) context.getBean("theaterServiceImpl");
		System.out.println(ts.getAllTheaters());
	}
	@Test
	public void testGetTheaterByName(){
		TheaterServiceImpl ts = (TheaterServiceImpl) context.getBean("theaterServiceImpl");
		System.out.println(ts.getTheaterByName("AMC Mercado"));
		Assert.assertEquals("name=" + "AMC Mercado" + ", address=" + "Santa Clara" + ", availability=" + 20 + "", ts.getTheaterByName("AMC Mercado").toString());
	}
}
