package com.appGym.webGym.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.appGym.webGym.entities.Day;
import com.appGym.webGym.entities.EvidenceExercise;
import com.appGym.webGym.entities.Exercise;
import com.appGym.webGym.entities.Training;

public class EvidenceExerciseDAOImplements implements EvidenceExerciseDAO {
	@Inject
	private Session session;

	@CommitAfter
	public boolean save(EvidenceExercise newEvidenceExercise) {
		// TODO Auto-generated method stub
		if (newEvidenceExercise != null) {
			session.save(newEvidenceExercise);
			return true;
		}
		return false;
	}

	@CommitAfter
	public boolean delete(EvidenceExercise newEvidenceExercise) {
		// TODO Auto-generated method stub
		if (newEvidenceExercise != null) {
			session.delete(newEvidenceExercise);
			return true;
		}
		return false;
	}

	@CommitAfter
	public boolean update(EvidenceExercise newEvidenceExercise) {
		// TODO Auto-generated method stub
		if (newEvidenceExercise != null) {
			session.update(newEvidenceExercise);
			return true;
		}
		return false;
	}

	public List<EvidenceExercise> findAll() {
		// TODO Auto-generated method stub
		return session.createCriteria(EvidenceExercise.class).list();
	}

	public EvidenceExercise findByTypeAndVideo(String type, String video) {
		// TODO Auto-generated method stub
		return null;
	}

	public EvidenceExercise findByID(Long id) {
		// TODO Auto-generated method stub
		if (id == null) {
			return null;
		}
		List<EvidenceExercise> list = session.createCriteria(EvidenceExercise.class).list();
		EvidenceExercise e = null;
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getId().equals(id)){
			return	e=(EvidenceExercise) list.get(i);
			}
		}
		return null;
	}

	@CommitAfter
	public boolean deleteByID(Long id) {
		EvidenceExercise e = findByID(id);
		if (e != null) {
			session.delete(e);
			return true;
		}
		return false;
	}

	public EvidenceExercise findByDay(Day day) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<EvidenceExercise> getExercises(Training traning) {
		// TODO Auto-generated method stub
		List<EvidenceExercise>pom= new ArrayList<EvidenceExercise>();
		List<EvidenceExercise> list = session.createCriteria(EvidenceExercise.class).list();
		System.out.println(traning.getName());
		for (int i = 0; i < list.size(); i++) {
			System.out.println("LIsta "+list.get(i).getTraining());
//			if(list.get(i).getTraining().getId().equals(traning.getId())){
//				pom.add(list.get(i));
//			}
		}
		if(pom!=null){
			return list;
		}
		return null;
	}
	
}
