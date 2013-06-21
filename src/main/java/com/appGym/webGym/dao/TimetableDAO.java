package com.appGym.webGym.dao;

import java.util.List;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;

import com.appGym.webGym.entities.Membership;
import com.appGym.webGym.entities.TimetableE;

public interface TimetableDAO {

	@CommitAfter
	public boolean save(TimetableE timetable);

	@CommitAfter
	public boolean delete(TimetableE timetable);

	@CommitAfter
	public boolean update(TimetableE timetable);

	public List<TimetableE> findAll();


	public TimetableE findByID(Long id);

	@CommitAfter
	public boolean deleteByID(Long id);
	
	public void deleteAll();
	
}
