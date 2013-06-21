package com.appGym.webGym.dao;

import java.util.List;

import org.apache.tapestry5.annotations.Log;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.appGym.webGym.entities.User;

public class UserDAOImplements implements UserDAO {
	@Inject
	private Session session;

	@CommitAfter
	public boolean save(User newUser) {
		// TODO Auto-generated method stub
		if (newUser != null) {
			session.save(newUser);
			return true;
		}
		return false;
	}

	@Log
	public boolean delete(User user) {
		if (user != null) {
			session.delete(user);
			return true;
		}
		return false;
	}

	@Log
	public boolean update(User user) {
		// TODO Auto-generated method stub
		if (user != null) {
			session.update(user);
			return true;
		}
		return false;
	}

	public List<User> findAll() {
		// TODO Auto-generated method stub
		return session.createCriteria(User.class).list();
	}

	@Log
	public User findByUserNameAndPassword(String username, String password) {
		// TODO Auto-generated method stub
		if (password == null || username == null) {
			return null;
		}

		if (password.equals("") || username.equals("")) {
			return null;
		}

		List l = session.createCriteria(User.class)
				.add(Restrictions.eq("username", username))
				.add(Restrictions.eq("password", password)).list();
		User u = null;
		if (l != null) {
			if (l.size() > 0) {
				u = (User) l.get(0);
			}
		}
		return u;
	}

	@Log
	public User findByID(Long id) {
		// TODO Auto-generated method stub
		if (id != null) {
			return (User) session.get(User.class, id);
		}
		return null;
	}

	@Log
	public boolean deleteByID(Long id) {
		// TODO Auto-generated method stub
		User u = findByID(id);
		if (u != null) {
			delete(u);
			return true;
		}
		return false;
	}
	
	public List<User> findUserByType(String type){
		List<User> users =session.createCriteria(User.class).add(Restrictions.eq("type", type)).list();
		return users;
	}

}
