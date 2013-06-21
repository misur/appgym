package com.appGym.webGym.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.appGym.webGym.entities.Membership;
import com.appGym.webGym.entities.Presence;
import com.appGym.webGym.entities.User;

public class PresenceDAOImplements implements PresenceDao{
	@Inject
	private Session session;
	@CommitAfter
	public boolean save(Presence presence) {
		// TODO Auto-generated method stub
		if (presence != null) {
			session.save(presence);
			return true;
		}
		return false;
	}

	@CommitAfter
	public boolean delete(Presence presence) {
		// TODO Auto-generated method stub
		if (presence != null) {
			session.delete(presence);
			return true;
		}
		return false;
	}

	@CommitAfter
	public boolean update(Presence presence) {
		// TODO Auto-generated method stub
		if (presence != null) {
			session.update(presence);
			return true;
		}
		return false;
	}

	public List<Presence> findAll() {
		// TODO Auto-generated method stub
		return session.createCriteria(Presence.class).list();
	}

	public Presence findByID(Long id) {
		// TODO Auto-generated method stub
		if (id == null) {
			return null;
		}
		List list = session.createCriteria(Presence.class)
				.add(Restrictions.eq("presence_id", id)).list();
		Presence p = null;
		if (list != null) {
			if (list.size() > 0) {
				p = (Presence) list.get(0);
				return p;
			}
		}
		return null;
	}

	@CommitAfter
	public boolean deleteByID(Long id) {
		// TODO Auto-generated method stub
		Presence p = findByID(id);
		if (p != null) {
			session.delete(p);
			return true;
		}
		return false;
	}

	public boolean check(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<User> findAllUsers(Long id) {
//		// TODO Auto-generated method stub
//		List<Presence> list = findAll();
//		List<User >users = new ArrayList<User>();
//		for (int i = 0; i < list.size(); i++) {
//			if(list.get(i).getTimetable().getId()==id){
//				users.add(list.get(i).getUser());
//			}
//		}
		return null;
	}

}
