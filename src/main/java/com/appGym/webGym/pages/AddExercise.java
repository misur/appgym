package com.appGym.webGym.pages;

import java.util.List;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.appGym.webGym.dao.ExerciseDAO;
import com.appGym.webGym.entities.Exercise;

public class AddExercise {
	@Property
	private Exercise exerciseList;

	@Inject
	private ExerciseDAO exerciseDAO;

	@Property
	private String description;

	@Property
	private String type;
	
	@Property
	private List<Exercise>list = exerciseDAO.findAll();

	@Property
	private String videoLink;

	Object onSubmitFromForm() {
		Exercise exercise = new Exercise();
		exercise.setDescription(description);
		exercise.setType(type);
		exercise.setVideoLink(transformURL(videoLink));
		if (exercise != null) {
			exerciseDAO.save(exercise);
			return Trainings.class;
		}
		return null;
	}
	
	public String transformURL(String s){
		StringBuffer sb = new StringBuffer(s);
		sb.delete(0, sb.indexOf("=")+1);
		return sb.toString();
	}
	
}
