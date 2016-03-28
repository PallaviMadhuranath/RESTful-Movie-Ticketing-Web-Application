package com.pallavi.movieticket.repository;

import java.util.List;

import com.pallavi.movieticket.entity.User;

public interface UserRepository {

	/**
	 * 
	 * @param user
	 * @return the id of the newly added user
	 */
	long addUser(User user);

	/**
	 * 
	 * @param userId
	 * @return user with the a specified userId
	 */
	User getUser(long userId);
	
	List<User> search(String firstName, String lastName);

}
