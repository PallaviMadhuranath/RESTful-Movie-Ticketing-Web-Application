package com.pallavi.movieticket.repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pallavi.movieticket.entity.Theater;
import com.pallavi.movieticket.entity.impl.TheaterImpl;
import com.pallavi.movieticket.repository.TheaterRepository;

/**
 * This class implements TheaterRepository interface. All the methods are
 * defined here. it contains hard coded values of theater.
 * 
 * @author pallavidas
 *
 */
@Component
public class TheaterRepositoryImpl implements TheaterRepository {
	
	@Autowired
	private SessionFactory sessionFactory;
	

	/**
	 * Constructor initialization. Initializes theater list with hard coded
	 * values.
	 */
	public TheaterRepositoryImpl() {
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Theater> getAllTheaters() {
		Criteria crit = this.sessionFactory.getCurrentSession().createCriteria(TheaterImpl.class);
		List<Theater> theaters = crit.list();
		return theaters;
	}

	@Override
	public Theater getTheaterByName(String name) {
		
		Criteria crit = this.sessionFactory.getCurrentSession().createCriteria(TheaterImpl.class);
		@SuppressWarnings("unchecked")
		List<Theater> theaters = crit.list();
		for (Theater theater : theaters) {
			if (theater.getName().equalsIgnoreCase(name)) {
				return theater;
			}
		}

		return null;
	}

}