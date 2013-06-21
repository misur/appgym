package com.appGym.webGym.dao;

import java.util.List;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;

import com.appGym.webGym.entities.Membership;
import com.appGym.webGym.entities.Presence;
import com.appGym.webGym.entities.User;

public interface PresenceDao {

	@CommitAfter
	public boolean save(Presence presence);

	@CommitAfter
	public boolean delete(Presence presence);

	@CommitAfter
	public boolean update(Presence presence);

	public List<Presence> findAll();


	public Presence findByID(Long id);

	@CommitAfter
	public boolean deleteByID(Long id);
	
	public boolean check(User user);
	public List<User> findAllUsers(Long id);
}
