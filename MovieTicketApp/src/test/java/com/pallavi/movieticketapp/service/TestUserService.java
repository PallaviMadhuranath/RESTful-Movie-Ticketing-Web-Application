package com.pallavi.movieticketapp.service;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.pallavi.movieticket.entity.User;
import com.pallavi.movieticket.entity.impl.UserImpl;
import com.pallavi.movieticket.service.UserService;


@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class TestUserService extends AbstractJUnit4SpringContextTests {
private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserService userService;
	
	@Test
	public void testPassword(){
		UserImpl newUser = new UserImpl();
		newUser.setFirstName("new test"+new Random().nextInt(99999));
		newUser.setLastName("lastName");
		newUser.setPassword("1234");
		
		User added = userService.addUser(newUser);
		Assert.assertEquals(false, userService.isPasswordValid(0, "12345"));
		Assert.assertEquals(false, userService.isPasswordValid(added.getId(), "12345"));
		Assert.assertEquals(false, userService.isPasswordValid(added.getId(), null));
		Assert.assertEquals(true, userService.isPasswordValid(added.getId(), "1234"));
	}
	
	@Test
	public void addAndGetUser(){
		UserImpl newUser = new UserImpl();
		newUser.setFirstName("pallavi"+new Random().nextInt(99999));
		newUser.setLastName("Das");
		newUser.setPassword("1234");
		
		User added = userService.addUser(newUser);
		logger.info("user added "+added);
		Assert.assertNotEquals(0, added.getId());//this should have been created so not zero anymore
		Assert.assertEquals(newUser.getFirstName(), added.getFirstName());
		Assert.assertEquals(newUser.getLastName(), added.getLastName());
		Assert.assertEquals(newUser.getPassword(), added.getPassword());
		
		User found = userService.getUser(added.getId());
		Assert.assertEquals(found.getId(), added.getId());
		Assert.assertEquals(found.getFirstName(), added.getFirstName());
		Assert.assertEquals(found.getLastName(), added.getLastName());
		Assert.assertEquals(found.getPassword(), added.getPassword());
	}

}
