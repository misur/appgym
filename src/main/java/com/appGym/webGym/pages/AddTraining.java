package com.appGym.webGym.pages;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.services.PropertyAccess;

import com.appGym.webGym.annotations.AdminAccess;
import com.appGym.webGym.annotations.AnonymousAccess;
import com.appGym.webGym.annotations.TrainerAccess;
import com.appGym.webGym.annotations.UserAccess;
import com.appGym.webGym.dao.EvidenceExerciseDAO;
import com.appGym.webGym.dao.ExerciseDAO;
import com.appGym.webGym.dao.TrainingDAO;
import com.appGym.webGym.dao.UserDAO;
import com.appGym.webGym.entities.Day;
import com.appGym.webGym.entities.EvidenceExercise;
import com.appGym.webGym.entities.Exercise;
import com.appGym.webGym.entities.Gender;
import com.appGym.webGym.entities.Training;
import com.appGym.webGym.entities.User;
import com.appGym.webGym.model.user.GenericSelectModel;
import com.appGym.webGym.model.user.MD5;
import com.appGym.webGym.services.Authenticator;

@AdminAccess
@TrainerAccess
public class AddTraining {
	@Property
	private String description;
	@Property
	private String type;
	@Property
	private String name;
	@Property
	private double price;
	@Inject
	private TrainingDAO trainingDAO;
	@Inject
	private Authenticator authenticator;
	@Property
	private Day selectedDays;
	private GenericSelectModel<Day> days;
	@Property
	private String selectedTypes;

	@Inject
	private PropertyAccess access;
	@Persist
	@Property
	private boolean typeTrainig;

	@Persist
	@Property
	private String test;
	
	@InjectPage
	private AddPersonalTraining addPersonalTraining;

	void onActive() {

	}

	public GenericSelectModel<Day> getDays() {
		List<Day> arr = Arrays.asList(Day.MONDAY, Day.TUESDAY, Day.WENESDAY,
				Day.THURSDAY, Day.FRIDAY, Day.SATURDAY, Day.SUNDAY);
		return new GenericSelectModel<Day>(arr, Day.class, null, null, access);
	}

	Object onSubmitFromForm() {
		if (typeTrainig == true) {
			selectedTypes = "personal";
		} else {
			selectedTypes = "group";
		}

		Training t = new Training();
		t.setPrice(price);
		t.setDescription(description);
		t.setName(name);
		t.setType(selectedTypes);
		t.setTrainer(authenticator.getLoggedUser());
		if(t.getName()==null||t.getDescription()==null||t.getType()==null||t.getPrice()==0){
			return null;
		}
		trainingDAO.save(t);
		if(typeTrainig==true){
			addPersonalTraining.setTraining2(t);
			return addPersonalTraining;
		}else{
			return Trainings.class;
			
		}
		
	}

	@Property
	@Persist
	private EvidenceExercise evidenceExercise;

	@Property
	private Exercise selectedExercise;
	@Property
	private GenericSelectModel<Exercise> exercises;

	@Property
	private Boolean deleted;

	

	@Inject
	private ExerciseDAO exerciseDAO;
	@Inject
	private EvidenceExerciseDAO evidenceExerciseDAO;

//	@OnEvent(EventConstants.ACTIVATE)
//	void init() {
//		if (evidenceExercise == null)
//			evidenceExercise = new EvidenceExercise();
//	}
//
//	public GenericSelectModel<Exercise> getExercisess() {
//		List<Exercise> arr = exerciseDAO.findAll();
//		return new GenericSelectModel<Exercise>(arr, Exercise.class, null,
//				null, access);
//	}
//
//	@OnEvent(EventConstants.SUCCESS)
//	public Object onSuccess() {
//
//		return this;
//	}
//
//	@OnEvent(value = EventConstants.ADD_ROW, component = "evidenceExercise")
//	public Object onAddRowFromPhones() {
//		evidenceExercise = new EvidenceExercise();
//		evidenceExercise.setDay(selectedDays);
//		Training t=  new Training();
//		evidenceExercise.setTrainig(t);
//		evidenceExercise.setExercise(selectedExercise);
//
//		return null;
//	}
//
//	@OnEvent(value = EventConstants.REMOVE_ROW, component = "evidenceExercise")
//	void onRemoveRowFromPhones(Day day) {
//		// evidenceExerciseDAO.findByDay(day).;
//	}
}
