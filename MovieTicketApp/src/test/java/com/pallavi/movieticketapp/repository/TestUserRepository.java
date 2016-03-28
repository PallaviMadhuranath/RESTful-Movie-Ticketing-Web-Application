package com.pallavi.movieticketapp.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.pallavi.movieticket.entity.User;
import com.pallavi.movieticket.entity.impl.UserImpl;
import com.pallavi.movieticket.repository.UserRepository;

@ContextConfiguration(locations = { "classpath:spring-context.xml" })
public class TestUserRepository extends AbstractTransactionalJUnit4SpringContextTests {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SessionFactory sessionFactory;

	@Test
	public void addAndGetUser() {
		UserImpl newUser = new UserImpl();
		newUser.setFirstName("pal" + new Random().nextInt(99999));
		newUser.setLastName("das");
		newUser.setEmailAddress("pal@gmail.com");
		newUser.setPassword("1234");

		long addedUserId = userRepository.addUser(newUser);
		System.out.println("user added id " + addedUserId);
		Assert.assertNotEquals(0, addedUserId);

		User found = userRepository.getUser(addedUserId);
		Assert.assertEquals(found.getId(), addedUserId);
		Assert.assertEquals(found.getFirstName(), newUser.getFirstName());
		Assert.assertEquals(found.getLastName(), newUser.getLastName());
		Assert.assertEquals(found.getPassword(), newUser.getPassword());
	}

	/*@Test
	public void searchUser(){
		List<User> users = userRepository.search("Pallavi", "Das");
		Assert.assertEquals(1,users.size());
	}*/

}
