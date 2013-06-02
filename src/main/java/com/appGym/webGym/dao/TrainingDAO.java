package com.appGym.webGym.dao;

import java.util.List;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;

import com.appGym.webGym.entities.Training;

public interface TrainingDAO {
	@CommitAfter
	public boolean save(Training training);

	@CommitAfter
	public boolean delete(Training training);

	@CommitAfter
	public boolean update(Training training);

	public List<Training> findAll();

	public Training findByID(Long id);

	@CommitAfter
	public boolean deleteByID(Long id);

	public List<Training> findGroupTrainings();

	public List<Training> findPersonalTrainings();

	public List<Training> findByName(String name);
}
