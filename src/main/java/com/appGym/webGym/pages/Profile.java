package com.appGym.webGym.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BeanModelSource;

import com.appGym.webGym.annotations.AdminAccess;
import com.appGym.webGym.annotations.TrainerAccess;
import com.appGym.webGym.annotations.UserAccess;
import com.appGym.webGym.dao.MembershipDAO;
import com.appGym.webGym.dao.MoreInformationDao;
import com.appGym.webGym.dao.MyTrainingsDAO;
import com.appGym.webGym.entities.Membership;
import com.appGym.webGym.entities.MoreInformation;
import com.appGym.webGym.entities.MyTrainings;
import com.appGym.webGym.entities.User;
import com.appGym.webGym.services.Authenticator;

@UserAccess
@AdminAccess
@TrainerAccess
public class Profile {

	@Property
	private User user;

	@Inject
	private Authenticator authenticator;

	@Property
	private MyTrainings myTraining;

	@Property
	private ArrayList<MyTrainings> myTrainings;

	@Inject
	private MyTrainingsDAO myTrainingsDAO;

	@Property
	private int currentIndex;

	@Inject
	private BeanModelSource _beanModelSource;

	@Inject
	private ComponentResources _componentResources;

	@Inject
	private MembershipDAO membershipDAO;
	@Property
	private Membership membership;

	@Property
	private MoreInformation moreInformation;

	@Inject
	private MoreInformationDao moreInformationDao;

	void onActivate() {
		user = authenticator.getLoggedUser();
		myTrainings = (ArrayList<MyTrainings>) myTrainingsDAO
				.getTrainings(user);
		if (moreInformationDao.findByUser(user.getId()) != null) {
			moreInformation = moreInformationDao.findByUser(user.getId());
		}
		if (membershipDAO.findMembershipByUser(user.getId()) != null) {
			membership = membershipDAO.findMembershipByUser(user.getId());
		}
	}

	public BeanModel getMyModel() {
		BeanModel myModel = _beanModelSource.createDisplayModel(
				MyTrainings.class, _componentResources.getMessages());
		myModel.add("TrainingName", null);
		myModel.add("Trainer", null);
		myModel.add("Type", null);
		myModel.add("Duration", null);
		myModel.include("trainingName", "trainer", "presence", "type",
				"duration");
		return myModel;

	}

}
