package com.pallavi.movieticket.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.pallavi.movieticket.entity.User;
import com.pallavi.movieticket.entity.impl.UserImpl;
import com.pallavi.movieticket.repository.UserRepository;
import com.pallavi.movieticket.service.UserService;
import com.pallavi.movieticket.service.exception.ErrorCode;
import com.pallavi.movieticket.service.exception.MovieTicketException;

@Service
public class UserServiceImpl implements UserService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	@Transactional//at method level // it is where database session will start.
	public User getUser(long userId) {
		return userRepository.getUser(userId);
	}
	
	@Override
	@Transactional//at method level
	public boolean isPasswordValid(long userId, String password) {
		User user = getUser(userId);
		if(user==null || password==null){
			return false;
		}
		return user.getPassword()!=null && user.getPassword().equals(md5base64(password));
	}

	@Override	
	@Transactional//at method level
	public User addUser(User user) {
		if(user.getPassword()==null){
			//TODO what is our exception handling strategy?
			throw new IllegalArgumentException("Password is required");
		}
		
		//let us hash the pin - TBTF bank does basic MD5
		UserImpl impl = (UserImpl)user;
		impl.setPassword(md5base64(user.getPassword()));		
		long id =  userRepository.addUser(user);
		return getUser(id);
	}
	
	private String md5base64(String password){
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] digest = md.digest(password.getBytes("UTF-8"));
			return Base64.encodeBase64String(digest);				
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			logger.error("failed to md5",e);
		}
		
		//TODO - this needs to be handled better
		throw new IllegalArgumentException("Server fail");
	}

	@Override
	@Transactional
	public List<User> getUsers(String firstName, String lastName) {
		List<User> returnList = new ArrayList<>();
		if(StringUtils.isEmpty(firstName) && StringUtils.isEmpty(lastName)){
			throw new MovieTicketException(ErrorCode.MISSING_DATA, "no search parameter provided");	
		}
		else{
			returnList = userRepository.search(firstName, lastName);
		}		
		return returnList;
	}

}
