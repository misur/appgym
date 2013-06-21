package com.appGym.webGym.pages;

import java.util.List;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BeanModelSource;

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
public class TrainerProfile {

	@Property
	private User user;

	@Inject
	private Authenticator autenticator;

	@Property
	private int currentIndex;

	@Inject
	private BeanModelSource _beanModelSource;

	@Inject
	private ComponentResources _componentResources;

	@Property
	private int numberOfTraining;
	@Property
	private int numberOfPeopleTraining;

	@Inject
	private TrainingDAO trainingDAO;
	@Inject
	private MyTrainingsDAO myTrainingsDAO;

	@Property
	private Training training;

	@Property
	private List<Training> trainingList ;

	void onActivate() {
		user = autenticator.getLoggedUser();
		numberOfTraining = trainingDAO.numberOfTraining(user.getId());
		numberOfPeopleTraining = myTrainingsDAO.numberOfPeopleOnTraining(user
				.getId());
		trainingList= trainingDAO.findMyTrainings(user.getId());
	}

	
	public BeanModel getMyModel1() {
		BeanModel myModel = _beanModelSource.createDisplayModel(Training.class,
				_componentResources.getMessages());
		myModel.add("action", null);
		myModel.include("name","type","rating","price","action");
		return myModel;
	}

}
