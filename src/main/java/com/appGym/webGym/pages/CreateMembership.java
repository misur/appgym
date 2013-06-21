package com.appGym.webGym.pages;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PropertyKey;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.appGym.webGym.annotations.AdminAccess;
import com.appGym.webGym.annotations.AnonymousAccess;
import com.appGym.webGym.annotations.UserAccess;
import com.appGym.webGym.dao.MembershipDAO;
import com.appGym.webGym.dao.MyTrainingsDAO;
import com.appGym.webGym.entities.Membership;
import com.appGym.webGym.entities.MyTrainings;
import com.appGym.webGym.entities.Training;
import com.appGym.webGym.entities.User;
import com.appGym.webGym.services.Authenticator;

@AdminAccess
@UserAccess
public class CreateMembership {
	@Inject
	private MembershipDAO membershipDAO;

	@Property
	private User user;

	@Property
	private Date start;

	@Property
	private Date finish;

	@Property
	private Membership membership = new Membership();

	@Property
	private Double price;

	@Inject
	private Authenticator autenticator;

	@Inject
	private MyTrainingsDAO myTrainingsDAO;

	void onActivate() {
		Date now = Calendar.getInstance().getTime();
		start = now;
		Date finish = Calendar.getInstance().getTime();
		finish.setMonth(now.getMonth() + 1);
		this.finish = finish;
		user = getUserF();
		price = countPrice();
	}

	Object onSubmitFromForm() {
		membership = new Membership();
		Date now = Calendar.getInstance().getTime();
		membership.setStartDate(now);
		
		membership.setFinishDate(finish);
		membership.setPrice(countPrice());
		membership.setUser(user);
		if (membership.getUser() == null || membership.getStartDate() == null
				|| membership.getFinishDate() == null
				|| membership.getPrice() == null) {
			return null;
		} else {
			membershipDAO.save(membership);
			return Index.class;
		}
	}

	public User getUserF() {
		if (autenticator.isLoggedIn()) {
			if (autenticator.getLoggedUser().getType().equalsIgnoreCase("user")) {
				return autenticator.getLoggedUser();
			}
		}
		return null;
	}

	public double countPrice() {
		List<MyTrainings> trainings = myTrainingsDAO.getTrainings(getUserF());
		double price = 0;
		for (int i = 0; i < trainings.size(); i++) {
			price = price + trainings.get(i).getTraining().getPrice();
		}
		return price;
	}

	public User getChecks() {
		for (int i = 0; i < membershipDAO.findAll().size(); i++) {
			if (membershipDAO.findAll().get(i).getUser().getId()
					.equals(user.getId())) {
				return user;
			}
		}
		return null;
	}
}
