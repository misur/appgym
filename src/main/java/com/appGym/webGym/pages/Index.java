package com.appGym.webGym.pages;

import java.util.List;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.appGym.webGym.annotations.AdminAccess;
import com.appGym.webGym.annotations.AnonymousAccess;
import com.appGym.webGym.annotations.TrainerAccess;
import com.appGym.webGym.annotations.UserAccess;
import com.appGym.webGym.dao.MembershipDAO;
import com.appGym.webGym.dao.MyTrainingsDAO;
import com.appGym.webGym.dao.TrainingDAO;
import com.appGym.webGym.dao.UserDAO;
import com.appGym.webGym.entities.MyTrainings;
import com.appGym.webGym.entities.Training;
import com.appGym.webGym.entities.User;
import com.appGym.webGym.services.Authenticator;

@AnonymousAccess
@UserAccess
@TrainerAccess
@AdminAccess
public class Index
{
	@Inject
	private Authenticator autenticator;
	@Inject
	private MyTrainingsDAO myTrainingsDAO;
	
	@javax.inject.Inject
	private MembershipDAO membershipDAO;
	
	@Property
	private List<User> trainers;
	
	@Property
	private User trainer;
	
	@Property
	private Training training;
	
	@Inject
	private TrainingDAO trainingDAO;
	
	@Inject
	private UserDAO userDAO;
	
	void onActivate(){
		trainers = userDAO.findUserByType("trainer");
		List<Training> trainings =trainingDAO.findAll();
		training= trainings.get(trainings.size()-1);
	}
	
	public User getUserF() {
		if (autenticator.isLoggedIn()) {
			if (autenticator.getLoggedUser().getType()
					.equalsIgnoreCase("user")) {
				return autenticator.getLoggedUser();
			}
		}
		return null;
	}
	
	public User getMembership(){
		if(countPrice()>0){
				return getUserF();
		}
		return null;
	}
	
	public double countPrice(){
		List<MyTrainings>trainings=myTrainingsDAO.getTrainings(getUserF());
		if(trainings==null){
			return 0;
		}
		double price=0;
		for (int i = 0; i < trainings.size(); i++) {
			price=price+trainings.get(i).getTraining().getPrice();
		}
		return price;
	}
	
	
        
}
