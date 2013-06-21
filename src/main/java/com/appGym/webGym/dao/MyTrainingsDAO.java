package com.appGym.webGym.dao;

import java.util.List;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;

import com.appGym.webGym.entities.MyTrainings;
import com.appGym.webGym.entities.Training;
import com.appGym.webGym.entities.User;

public interface MyTrainingsDAO {
	@CommitAfter
	public boolean save(MyTrainings newMyTrainings);

	@CommitAfter
	public boolean delete(MyTrainings newMyTrainings);

	@CommitAfter
	public boolean update(MyTrainings newMyTrainings);

	public List<MyTrainings> findAll();

	public MyTrainings findByID(Long id);

	@CommitAfter
	public boolean deleteByID(Long id);

	public List<MyTrainings> getTrainings(User user);

	public MyTrainings findTrainingByUserIDAndTrainingID(Long id, User user);

	@CommitAfter
	public boolean deleteByID(Long id, User user);

	public List<MyTrainings> findAllUserFromTraining(Training training);

	public int numberOfPeopleOnTraining(Long id);

}
