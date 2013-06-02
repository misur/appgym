package com.appGym.webGym.pages;

import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.appGym.webGym.annotations.AdminAccess;
import com.appGym.webGym.annotations.TrainerAccess;
import com.appGym.webGym.annotations.UserAccess;
import com.appGym.webGym.dao.MyTrainingsDAO;
import com.appGym.webGym.dao.TrainingDAO;
import com.appGym.webGym.dao.UserDAO;
import com.appGym.webGym.entities.MyTrainings;
import com.appGym.webGym.entities.Training;
import com.appGym.webGym.entities.User;
import com.appGym.webGym.services.Authenticator;

@AdminAccess
@TrainerAccess
@UserAccess
public class DetailsTraining {
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
	

	Object onActivate(Long id) {
		training = trainingDAO.findByID(id);
		trainer = userDAO.findByID(training.getTrainer().getId());
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
			if(myTrainingsDAO.findTrainingByUserIDAndTrainingID(training.getId(),autenticator.getLoggedUser())!=null){
				return autenticator.getLoggedUser();
			}
		}
		return null;
	}

}
