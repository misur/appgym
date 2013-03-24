package com.appGym.webGym.dao;

import java.util.List;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;

import com.appGym.webGym.entities.User;

public interface UserDAO {

	@CommitAfter
	public boolean save(User newUser);

	@CommitAfter
	public boolean delete(User user);

	@CommitAfter
	public boolean update(User user);

	public List<User> findAll();

	public User findByUserNameAndPassword(String username, String password);

	public User findByID(Long id);

	@CommitAfter
	public boolean deleteByID(Long id);
}
