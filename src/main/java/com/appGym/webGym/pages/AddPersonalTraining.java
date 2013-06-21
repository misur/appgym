package com.appGym.webGym.pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.services.PropertyAccess;

import com.appGym.webGym.annotations.AdminAccess;
import com.appGym.webGym.annotations.TrainerAccess;
import com.appGym.webGym.dao.EvidenceExerciseDAO;
import com.appGym.webGym.dao.ExerciseDAO;
import com.appGym.webGym.dao.TrainingDAO;
import com.appGym.webGym.entities.Day;
import com.appGym.webGym.entities.EvidenceExercise;
import com.appGym.webGym.entities.Exercise;
import com.appGym.webGym.entities.Training;
import com.appGym.webGym.model.user.GenericSelectModel;

@AdminAccess
@TrainerAccess
public class AddPersonalTraining {
	@Property
	private Training training;

	@Property
	@Persist
	private EvidenceExercise evidenceExercise;

	@Property
	private Exercise exercise;

	@Property
	private Boolean deleted;

	@Property
	private GenericSelectModel<Exercise> exercises;

	@Inject
	private PropertyAccess access;
	
	@Inject
	private TrainingDAO trainingDAO;

	@Inject
	private ExerciseDAO exerciseDAO;
	
	@Inject
	private EvidenceExerciseDAO evidenceExerciseDAO;

	@Property
	private Day selectedDays;
	private GenericSelectModel<Day> days;
	
	@Persist
	private Training training2;
	
	@Property
	private List<Exercise>list2 = exerciseDAO.findAll();
	
	@Property
	private List<EvidenceExercise> list ;
	
	private List<Long> listForDelete =new ArrayList<Long>() ;
	
	@Property
	private boolean visibility;
	
	@Property
	private Exercise exerciseList;


	@Property
	private String description;

	@Property
	private String type;

	@Property
	private String videoLink;
 
	void onActivate(){
		list = new ArrayList<EvidenceExercise>();
		list =findExercise(training2);
	}
	
	@OnEvent(EventConstants.ACTIVATE)
	void init() {
		if (evidenceExercise == null)
			evidenceExercise = new EvidenceExercise();
	}

	public GenericSelectModel<Exercise> getExercisesList() {
		List<Exercise> arr = exerciseDAO.findAll();
		return new GenericSelectModel<Exercise>(arr, Exercise.class, null,
				null, access);
	}

	public GenericSelectModel<Day> getDays() {
		List<Day> arr = Arrays.asList(Day.MONDAY, Day.TUESDAY, Day.WENESDAY,
				Day.THURSDAY, Day.FRIDAY, Day.SATURDAY, Day.SUNDAY);
		return new GenericSelectModel<Day>(arr, Day.class, null, null, access);
	}



	public Training getTraining2() {
		return training2;
	}

	public void setTraining2(Training training2) {
		this.training2 = training2;
	}
	
	Object onSubmitFromExerciseForm()   {
		evidenceExercise=  new EvidenceExercise();
		evidenceExercise.setDay(selectedDays);
		evidenceExercise.setTraining(training2);
		evidenceExercise.setExercise(exercise);
		
		if(evidenceExercise.getDay()==null||evidenceExercise.getExercise()==null||evidenceExercise.getTraining()==null){
			return null;
		}
		
		
		evidenceExerciseDAO.save(evidenceExercise);
		listForDelete.add(evidenceExercise.getId());
		System.out.println(evidenceExercise.getId());
		return null;
	}
	
	public List<EvidenceExercise> findExercise(Training training) {
		List<EvidenceExercise> list = evidenceExerciseDAO.findAll();
		List<EvidenceExercise> list2= new ArrayList<EvidenceExercise>();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getTraining().getId().equals(training.getId())) {
				list2.add(list.get(i));
			}
		}
		return list2;
	}
	
	Object onActionFromDelete(Long evidenceExerciseID) {
		System.out.println(evidenceExerciseDAO.findByID(evidenceExerciseID));
		evidenceExerciseDAO.deleteByID(evidenceExerciseID);
		list =findExercise(training2);
		return null;
	}
	
	Object onActionFromCancel() {
		
		for (int i = 0; i < listForDelete.size(); i++) {
			System.out.println(listForDelete.get(i));
			evidenceExerciseDAO.deleteByID(listForDelete.get(i));
		}
		trainingDAO.deleteByID(training2.getId());
		return Trainings.class;
	}
	void onActionFromVisible() {
		System.out.println("Prikazi");
		this.visibility=true;
	}
	
	

	boolean onSubmitFromForm() {
		Exercise exercise = new Exercise();
		exercise.setDescription(description);
		exercise.setType(type);
		exercise.setVideoLink(transformURL(videoLink));
		if (exercise != null) {
			exerciseDAO.save(exercise);
			return true;
		}
		return false;
	}
	
	public String transformURL(String s){
		StringBuffer sb = new StringBuffer(s);
		sb.delete(0, sb.indexOf("=")+1);
		return sb.toString();
	}
	
}
