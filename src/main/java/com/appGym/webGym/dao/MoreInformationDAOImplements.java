package com.appGym.webGym.dao;

import java.util.List;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.appGym.webGym.entities.Membership;
import com.appGym.webGym.entities.MoreInformation;
import com.appGym.webGym.entities.User;

public class MoreInformationDAOImplements implements MoreInformationDao {
	@Inject
	private Session session;

	@CommitAfter
	public boolean save(MoreInformation newMoreInformation) {
		// TODO Auto-generated method stub
		if (newMoreInformation != null) {
			session.save(newMoreInformation);
			return true;
		}
		return false;
	}

	@CommitAfter
	public boolean delete(MoreInformation newMoreInformation) {
		if (newMoreInformation != null) {
			session.delete(newMoreInformation);
			return true;
		}
		return false;
	}

	@CommitAfter
	public boolean update(MoreInformation newMoreInformation) {
		// TODO Auto-generated method stub
		if (newMoreInformation != null) {
			session.update(newMoreInformation);
			return true;
		}
		return false;
	}

	public List<MoreInformation> findAll() {
		// TODO Auto-generated method stub
		return session.createCriteria(MoreInformation.class).list();
	}

	public MoreInformation findByID(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@CommitAfter
	public boolean deleteByID(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	public MoreInformation findByUser(Long id) {
		// TODO Auto-generated method stub
		List<MoreInformation>list =session.createCriteria(MoreInformation.class).list();
		if(list.size()!=0){
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getUser().getId()==id){
				return list.get(i);
			}
		}
		}
		return null;
	}
}
