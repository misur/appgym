package com.appGym.webGym.dao;

import java.util.List;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;

import com.appGym.webGym.entities.Day;
import com.appGym.webGym.entities.EvidenceExercise;
import com.appGym.webGym.entities.Exercise;
import com.appGym.webGym.entities.Training;


public interface EvidenceExerciseDAO {

	@CommitAfter
	public boolean save(EvidenceExercise newEvidenceExercise);

	@CommitAfter
	public boolean delete(EvidenceExercise evidenceExercise);

	@CommitAfter
	public boolean update(EvidenceExercise evidenceExercise);

	public List<EvidenceExercise> findAll();

	public EvidenceExercise  findByDay(Day day);

	public EvidenceExercise findByID(Long id);

	@CommitAfter
	public boolean deleteByID(Long id);
	
	public List<EvidenceExercise> getExercises(Training traning);
		
	

}
