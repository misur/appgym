package com.appGym.webGym.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.appGym.webGym.annotations.AdminAccess;
import com.appGym.webGym.annotations.TrainerAccess;
import com.appGym.webGym.annotations.UserAccess;
import com.appGym.webGym.dao.EvidenceExerciseDAO;
import com.appGym.webGym.dao.ExerciseDAO;
import com.appGym.webGym.dao.MyTrainingsDAO;
import com.appGym.webGym.dao.TrainingDAO;
import com.appGym.webGym.dao.UserDAO;
import com.appGym.webGym.entities.EvidenceExercise;
import com.appGym.webGym.entities.Exercise;
import com.appGym.webGym.entities.MyTrainings;
import com.appGym.webGym.entities.Training;
import com.appGym.webGym.entities.User;
import com.appGym.webGym.services.Authenticator;

@AdminAccess
@TrainerAccess
@UserAccess
public class DetailsPersonalTraining {
	@Persist
	@Property
	private Training training;

	@Inject
	private TrainingDAO trainingDAO;

	@Inject
	private UserDAO userDAO;

	@Inject
	private MyTrainingsDAO myTrainingsDAO;

	@Inject
	private Authenticator autenticator;

	@Persist
	@Property
	private User trainer;

	@Property
	private EvidenceExercise evidenceExercise;
	@Inject
	private ExerciseDAO exerciseDAO;
	@Inject
	private EvidenceExerciseDAO evidenceExerciseDAO;
	
	@Property
	private List<EvidenceExercise> list ;

	Object onActivate(Long id) {
		training = trainingDAO.findByID(id);
		trainer = userDAO.findByID(training.getTrainer().getId());
		list = new ArrayList<EvidenceExercise>();
		list =findExercise(training);
		return null;
	}

	Object onSubmitFromForm() {
		MyTrainings myTrainings = new MyTrainings();
		myTrainings.setTrainig(training);
		myTrainings.setUser(autenticator.getLoggedUser());
		myTrainingsDAO.save(myTrainings);
		return Trainings.class;
	}

	public User check() {

		return null;
	}

	public User getUser() {
		if (autenticator.isLoggedIn()) {
			if (myTrainingsDAO.findTrainingByUserIDAndTrainingID(
					training.getId(), autenticator.getLoggedUser()) != null) {
				return autenticator.getLoggedUser();
			}
		}
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

	public List<Exercise> exercises() {
		return exerciseDAO.findAll();
	}
	

}
