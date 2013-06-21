package com.appGym.webGym.pages;

import javax.persistence.Column;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.appGym.webGym.annotations.AdminAccess;
import com.appGym.webGym.annotations.TrainerAccess;
import com.appGym.webGym.annotations.UserAccess;
import com.appGym.webGym.dao.MoreInformationDao;
import com.appGym.webGym.entities.MoreInformation;
import com.appGym.webGym.entities.User;
import com.appGym.webGym.services.Authenticator;

@UserAccess
@AdminAccess
@TrainerAccess
public class AddMoreInformation {
	
	@Property
	private User user;
	@Property
	private MoreInformation moreInformation;
	@Inject
	private MoreInformationDao moreInformationDao;
	@Property
	private int weight;
	@Property
	private int height;
	@Property
	private int fatPercentage;
	@Property
	private int percentageOfMuscleMass;
	@Property
	private int volumeOfArms;
	@Property
	private int extentOfLegs;
	@Property
	private int vaist;
	
	@Inject
	private Authenticator authenticator;
	
	void onActivate(){
		user =authenticator.getLoggedUser();
	}
	
	Object onSubmitFromForm1() {
		moreInformation = new MoreInformation();
		moreInformation.setUser(user);
		moreInformation.setExtentOfLegs(extentOfLegs);
		moreInformation.setFatPercentage(fatPercentage);
		moreInformation.setHeight(height);
		moreInformation.setPercentageOfMuscleMass(percentageOfMuscleMass);
		moreInformation.setVaist(vaist);
		moreInformation.setVolumeOfArms(volumeOfArms);
		moreInformation.setWeight(weight);
		moreInformationDao.save(moreInformation);
		return Index.class;
	}
	

}
