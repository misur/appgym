package com.appGym.webGym.dao;

import java.util.List;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.appGym.webGym.entities.Exercise;

public class ExerciseDAOImplements implements ExerciseDAO {
	@Inject
	private Session session;

	@CommitAfter
	public boolean save(Exercise newExercise) {
		// TODO Auto-generated method stub
		if (newExercise != null) {
			session.save(newExercise);
			return true;
		}
		return false;
	}

	@CommitAfter
	public boolean delete(Exercise exercise) {
		// TODO Auto-generated method stub
		if (exercise != null) {
			session.delete(exercise);
			return true;
		}
		return false;
	}

	@CommitAfter
	public boolean update(Exercise exercise) {
		// TODO Auto-generated method stub
		if (exercise != null) {
			session.update(exercise);
			return true;
		}
		return false;
	}

	public List<Exercise> findAll() {
		// TODO Auto-generated method stub
		return session.createCriteria(Exercise.class).list();
	}

	public Exercise findByTypeAndVideo(String type, String video) {
		// TODO Auto-generated method stub
		return null;
	}

	public Exercise findByID(Long id) {
		// TODO Auto-generated method stub
		if (id == null) {
			return null;
		}
		List list = session.createCriteria(Exercise.class)
				.add(Restrictions.eq("exercise_id", id)).list();
		Exercise e = null;
		if (list != null) {
			if (list.size() > 0) {
				e = (Exercise) list.get(0);
			}
		}
		return null;
	}

	@CommitAfter
	public boolean deleteByID(Long id) {
		Exercise e = findByID(id);
		if (e != null) {
			session.delete(e);
			return true;
		}
		return false;
	}
}
