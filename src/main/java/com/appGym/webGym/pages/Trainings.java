package com.appGym.webGym.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.appGym.webGym.annotations.AdminAccess;
import com.appGym.webGym.annotations.AnonymousAccess;
import com.appGym.webGym.annotations.TrainerAccess;
import com.appGym.webGym.annotations.UserAccess;
import com.appGym.webGym.dao.MyTrainingsDAO;
import com.appGym.webGym.dao.TrainingDAO;
import com.appGym.webGym.entities.MyTrainings;
import com.appGym.webGym.entities.Training;
import com.appGym.webGym.entities.User;
import com.appGym.webGym.services.Authenticator;

@UserAccess
@TrainerAccess
@AdminAccess
public class Trainings {
	@Inject
	private TrainingDAO trainingDAO;
	
	@InjectPage
	private DetailsTraining detailsTraining;

	@Property
	private Training personaTraining;

	@Property
	private Training groupTraining;

	@Inject
	private Authenticator autenticator;
	
	@Inject
	private MyTrainingsDAO myTrainingsDAO;
	
	@Property
	@Persist
	private MyTrainings myTraining;
	private Training training;

//	@Property
//	private boolean logged = checkLoginType();

	private boolean checkLoginType() {
		if (autenticator.getLoggedUser().getType().equalsIgnoreCase("trainer"))
			return true;
		return false;
	}

	public List<Training> getGroupTrainings() {
		return trainingDAO.findGroupTrainings();
	}

	public List<Training> getPersonalTrainings() {
		return trainingDAO.findPersonalTrainings();
	}
	
	public List<MyTrainings> getMyTrainings() {
//		List<Training> listTrainings =  new ArrayList<Training>();
//		List<MyTrainings> list =myTrainingsDAO.getTrainings(autenticator.getLoggedUser());
//		for (int i = 0; i < list.size(); i++) {
//			listTrainings.add(trainingDAO.findByID(list.get(i).getTraining().getId()));
//		}
		return myTrainingsDAO.getTrainings(autenticator.getLoggedUser());
	}
	
	

	public User getUser() {
		if (autenticator.isLoggedIn()) {
			if (autenticator.getLoggedUser().getType()
					.equalsIgnoreCase("trainer")) {
				return autenticator.getLoggedUser();
			}
		}
		return null;
	}

	// @OnEvent(component = "print")
	// Object onSubmitFromForm() {
	// System.out.println("personal training " + getPersonalTrainings());
	// return null;
	// }
	
	@CommitAfter
	Object onActionFromDelete(Long myTrainingsId) {
		myTrainingsDAO.deleteByID(myTrainingsId,autenticator.getLoggedUser());
		return null;
	}

}
