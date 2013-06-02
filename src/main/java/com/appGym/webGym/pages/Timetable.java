package com.appGym.webGym.pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.services.PropertyAccess;

import com.appGym.webGym.annotations.AdminAccess;
import com.appGym.webGym.annotations.UserAccess;
import com.appGym.webGym.dao.TrainingDAO;
import com.appGym.webGym.entities.Training;
import com.appGym.webGym.model.user.GenericSelectModel;



@UserAccess
@AdminAccess
public class Timetable {
	@Property
	@Persist
	private Training selectedTraining;
	
	private GenericSelectModel<Training> trainings;
	
	@Inject
	private TrainingDAO trainingDAO;
	
	@Property
	private List<Training> timetableList = new ArrayList<Training>();

	@Inject
	private PropertyAccess access;
	
	@Property
	List<Training> arr= trainingDAO.findGroupTrainings() ;
	
	public GenericSelectModel<Training> getTrainings() {
		 arr= trainingDAO.findGroupTrainings();
		return new GenericSelectModel<Training>(arr, Training.class, null, null,
				access);
	}
}
