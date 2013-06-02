package com.appGym.webGym.dao;

import java.util.List;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.appGym.webGym.entities.Exercise;
import com.appGym.webGym.entities.Membership;

public class MembershipDAOImplements implements MembershipDAO {
	@Inject
	private Session session;

	@CommitAfter
	public boolean save(Membership newMembership) {
		// TODO Auto-generated method stub
		if (newMembership != null) {
			session.save(newMembership);
			return true;
		}
		return false;
	}

	@CommitAfter
	public boolean delete(Membership newMembership) {
		// TODO Auto-generated method stub
		if (newMembership != null) {
			session.delete(newMembership);
			return true;
		}
		return false;
	}

	@CommitAfter
	public boolean update(Membership newMembership) {
		// TODO Auto-generated method stub
		if (newMembership != null) {
			session.update(newMembership);
			return true;
		}
		return false;
	}

	public List<Membership> findAll() {
		// TODO Auto-generated method stub
		return session.createCriteria(Membership.class).list();
	}

	public Membership findByID(Long id) {
		// TODO Auto-generated method stub
		if (id == null) {
			return null;
		}
		List list = session.createCriteria(Membership.class)
				.add(Restrictions.eq("membership_id", id)).list();
		Membership m = null;
		if (list != null) {
			if (list.size() > 0) {
				m = (Membership) list.get(0);
			}
		}
		return null;
	}

	@CommitAfter
	public boolean deleteByID(Long id) {
		// TODO Auto-generated method stub
		Membership m = findByID(id);
		if (m != null) {
			session.delete(m);
			return true;
		}
		return false;
	}
	
}
