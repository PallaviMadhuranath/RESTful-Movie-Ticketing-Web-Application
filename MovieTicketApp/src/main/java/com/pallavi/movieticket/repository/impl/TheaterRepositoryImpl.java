package com.pallavi.movieticket.repository.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.pallavi.movieticket.entity.Movie;
import com.pallavi.movieticket.entity.Theater;
import com.pallavi.movieticket.entity.impl.MovieImpl;
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
		System.out.println("From Repo Impl");
		System.out.println(theaters.size());
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

	@Override
	public Theater getTheaterByID(long id) {

		return (Theater) this.sessionFactory.getCurrentSession().get(TheaterImpl.class, id);
	}

	@Override
	public List<Theater> getTheatersByName(String name) {

		Criteria crit = this.sessionFactory.getCurrentSession().createCriteria(TheaterImpl.class);
		if (!StringUtils.isEmpty(name)) {
			crit.add(Restrictions.eq("name", name));
		}
		@SuppressWarnings("unchecked")
		List<Theater> theaters = crit.list();

		Set<Theater> theatersSet = new HashSet<Theater>();
		for (Theater theater : theaters) {
			theatersSet.add(theater);
		}
		return new ArrayList<>(theatersSet);
	}

	@Override
	public long addTheater(Theater theater) {
		return (Long) this.sessionFactory.getCurrentSession().save(theater);
	}

	@Override
	public void deleteTheater(long id) {
		Theater theater = getTheaterByID(id);
		if (theater != null) {
			this.sessionFactory.getCurrentSession().delete(theater);
		}

	}

}
