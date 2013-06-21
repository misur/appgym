package com.appGym.webGym.pages;

import java.util.List;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Primary;
import org.apache.tapestry5.ioc.services.PropertyAccess;

import com.appGym.webGym.annotations.AdminAccess;
import com.appGym.webGym.annotations.TrainerAccess;
import com.appGym.webGym.annotations.UserAccess;
import com.appGym.webGym.dao.PresenceDao;
import com.appGym.webGym.dao.TimetableDAO;
import com.appGym.webGym.entities.Exercise;
import com.appGym.webGym.entities.Presence;
import com.appGym.webGym.entities.TimetableE;
import com.appGym.webGym.entities.User;
import com.appGym.webGym.model.user.GenericSelectModel;
import com.appGym.webGym.services.Authenticator;

@UserAccess
@AdminAccess
@TrainerAccess
public class AddPresence {
	
	@Property
	private Presence presence;
	
	@Inject
	private PresenceDao presenceDao;
	
	@Property
	private User user;
	
	@Property
	private TimetableE timetableE;
	
	@Property
	private boolean checkPresence;
	
	@Inject 
	private TimetableDAO timetableDAO;
	
	
	@Property
	private GenericSelectModel<Presence> presences;
	
	@Inject
	private PropertyAccess access;
	
	@Inject
	private Authenticator authenticator;
	
	public GenericSelectModel<TimetableE> getTimetableSelectModel() {
		List<TimetableE> arr = timetableDAO.findAll();
		return new GenericSelectModel<TimetableE>(arr, TimetableE.class, null,
				null, access);
	}
	
	void onAction(){
		user = authenticator.getLoggedUser();
	}
}
