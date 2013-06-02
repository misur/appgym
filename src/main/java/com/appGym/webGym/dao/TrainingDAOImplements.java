package com.appGym.webGym.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.tapestry5.annotations.Log;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.loader.criteria.CriteriaLoader;

import com.appGym.webGym.entities.Training;
import com.appGym.webGym.entities.User;

public class TrainingDAOImplements implements TrainingDAO {
	@Inject
	private Session session;

	@CommitAfter
	public boolean save(Training newTraining) {
		// TODO Auto-generated method stub
		if (newTraining != null) {
			session.save(newTraining);
			return true;
		}
		return false;
	}

	@Log
	public boolean delete(Training newTraining) {
		if (newTraining != null) {
			session.delete(newTraining);
			return true;
		}
		return false;
	}

	@Log
	public boolean update(Training newTraining) {
		// TODO Auto-generated method stub
		if (newTraining != null) {
			session.update(newTraining);
			return true;
		}
		return false;
	}

	public List<Training> findAll() {
		// TODO Auto-generated method stub
		return session.createCriteria(Training.class).list();
	}

	@Log
	public Training findByID(Long id) {
		// TODO Auto-generated method stub
		if (id != null) {
			return (Training) session.get(Training.class, id);
		}
		return null;
	}

	@Log
	public boolean deleteByID(Long id) {
		// TODO Auto-generated method stub
		Training t = findByID(id);
		if (t != null) {
			delete(t);
			return true;
		}
		return false;
	}

	public List<Training> findGroupTrainings() {
		// TODO Auto-generated method stub
		List<Training> t =session.createCriteria(Training.class)
				.add(Restrictions.eq("type", "group")).list();
		if(t==null){
			return null;
		}
		return t;
	}

	public List<Training> findPersonalTrainings() {
		// TODO Auto-generated method stub
		List<Training> t =session.createCriteria(Training.class).add(Restrictions.eq("type", "personal")).list();
		if(t==null){
			return null;
		}
		return t;	
	}
	public List<Training> findByName(String name) {
		// TODO Auto-generated method stub
		List<Training> t =session.createCriteria(Training.class).add(Restrictions.eq("name", "name")).list();
		if(t==null){
			return null;
		}
		return t;	
	}

}
