package com.appGym.webGym.dao;

import java.util.List;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;

import com.appGym.webGym.entities.Membership;


public interface MembershipDAO {

	@CommitAfter
	public boolean save(Membership newMembership);

	@CommitAfter
	public boolean delete(Membership newMembership);

	@CommitAfter
	public boolean update(Membership newMembership);

	public List<Membership> findAll();


	public Membership findByID(Long id);

	@CommitAfter
	public boolean deleteByID(Long id);
}
