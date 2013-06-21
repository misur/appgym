package com.appGym.webGym.dao;

import java.util.List;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.appGym.webGym.entities.Membership;
import com.appGym.webGym.entities.TimetableE;
import com.appGym.webGym.entities.Training;

public class TimetableDAOImplements implements TimetableDAO{
	@Inject
	private Session session;
	
	@CommitAfter
	public boolean save(TimetableE timetable) {
		// TODO Auto-generated method stub
		if (timetable != null) {
			session.save(timetable);
			return true;
		}
		return false;
	}

	@CommitAfter
	public boolean delete(TimetableE timetable) {
		// TODO Auto-generated method stub
		if (timetable != null) {
			session.delete(timetable);
			return true;
		}
		return false;
	}

	@CommitAfter
	public boolean update(TimetableE timetable) {
		// TODO Auto-generated method stub
		if (timetable != null) {
			session.update(timetable);
			return true;
		}
		return false;
	}

	public List<TimetableE> findAll() {
		// TODO Auto-generated method stub
		return session.createCriteria(TimetableE.class).list();
	}

	public TimetableE findByID(Long id) {
		// TODO Auto-generated method stub
		if (id == null) {
			return null;
		}
		List list = session.createCriteria(TimetableE.class)
				.add(Restrictions.eq("membership_id", id)).list();
		TimetableE t = null;
		if (list != null) {
			if (list.size() > 0) {
				t = (TimetableE) list.get(0);
			}
		}
		return null;
	}

	@CommitAfter
	public boolean deleteByID(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	public void deleteAll() {
		// TODO Auto-generated method stub
		
		List<TimetableE> list = session.createCriteria(TimetableE.class).list();
		
		for (TimetableE object : list) {
			session.delete(object);
		}
	}
	
	


}
