package com.appGym.webGym.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.annotations.Log;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.appGym.webGym.entities.MyTrainings;
import com.appGym.webGym.entities.User;

public class MyTrainingsDAOImplements implements MyTrainingsDAO {
	@Inject
	private Session session;

	@CommitAfter
	public boolean save(MyTrainings newMyTrainings) {
		if (newMyTrainings != null) {
			session.save(newMyTrainings);
			return true;
		}
		return false;
	}

	@CommitAfter
	public boolean delete(MyTrainings myTrainings) {
		if (myTrainings != null) {
			session.delete(myTrainings);
			return true;
		}
		return false;
	}

	@CommitAfter
	public boolean update(MyTrainings newMyTrainings) {
		if (newMyTrainings != null) {
			session.update(newMyTrainings);
			return true;
		}
		return false;
	}

	public List<MyTrainings> findAll() {
		// TODO Auto-generated method stub
		return session.createCriteria(MyTrainings.class).list();
	}

	public MyTrainings findByTrainingID(Long id) {
		// TODO Auto-generated method stub
		if (id != null) {
			return (MyTrainings) session.get(MyTrainings.class, id);
		}
		return null;
	}

	public MyTrainings findTrainingByUserIDAndTrainingID(Long id ,User user){
		List<MyTrainings>list =session.createCriteria(MyTrainings.class).list();
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getPk().getTraining().getId().equals(id)&&list.get(i).getPk().getUser().getId().equals(user.getId())){
				return list.get(i);
			}
		}
		return null;
	}

	
	@CommitAfter
	public boolean deleteByID(Long id,User user) {
		MyTrainings t = findTrainingByUserIDAndTrainingID(id, user);
		if (t != null) {
			session.delete(t);
			return true;
		}
		return false;
	}
	
	@CommitAfter
	public boolean deleteByID(Long id) {
		// TODO Auto-generated method stub
		MyTrainings t = findByID(id);
		if (t != null) {
			session.delete(t);
			return true;
		}
		return false;
	}

	public List<MyTrainings> getTrainings(User user) {
		List<MyTrainings> tempList = session.createCriteria(MyTrainings.class)
				.list();
		List<MyTrainings> list = new ArrayList<MyTrainings>();
		for (int i = 0; i < tempList.size(); i++) {
			if (tempList.get(i).getUser().getId().equals(user.getId())) {
				list.add(tempList.get(i));
			}
		}
		return list;
	}

	public MyTrainings findByID(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}
