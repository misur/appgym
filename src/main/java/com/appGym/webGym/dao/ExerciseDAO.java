package com.appGym.webGym.dao;

import java.util.List;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;

import com.appGym.webGym.entities.Exercise;

public interface ExerciseDAO {

	@CommitAfter
	public boolean save(Exercise newExercise);

	@CommitAfter
	public boolean delete(Exercise exercise);

	@CommitAfter
	public boolean update(Exercise exercise);

	public List<Exercise> findAll();

	public Exercise  findByTypeAndVideo(String type, String video);

	public Exercise findByID(Long id);

	@CommitAfter
	public boolean deleteByID(Long id);
}
