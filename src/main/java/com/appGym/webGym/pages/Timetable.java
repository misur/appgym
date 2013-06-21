package com.appGym.webGym.pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.services.PropertyAccess;

import com.appGym.webGym.annotations.AdminAccess;
import com.appGym.webGym.annotations.TrainerAccess;
import com.appGym.webGym.annotations.UserAccess;
import com.appGym.webGym.dao.TimetableDAO;
import com.appGym.webGym.dao.TrainingDAO;
import com.appGym.webGym.entities.Day;
import com.appGym.webGym.entities.TimetableE;
import com.appGym.webGym.entities.Training;
import com.appGym.webGym.entities.User;
import com.appGym.webGym.model.user.GenericSelectModel;
import com.appGym.webGym.services.Authenticator;

@UserAccess
@AdminAccess
@TrainerAccess
public class Timetable {
	@Inject
	private TimetableDAO timetableDAO;
	@Property
	private TimetableE monday;
	@Property
	private List<TimetableE> mondayTrainings=new ArrayList<TimetableE>();
	@Property
	private TimetableE tuesday;
	@Property
	private List<TimetableE> tuesdayTrainings=new ArrayList<TimetableE>();
	@Property
	private TimetableE wednesday;
	@Property
	private List<TimetableE> wednesdayTrainings=new ArrayList<TimetableE>();
	@Property
	private TimetableE thursday;
	@Property
	private List<TimetableE> thursdayTrainings=new ArrayList<TimetableE>();
	@Property
	private TimetableE friday;
	@Property
	private List<TimetableE> fridayTrainings=new ArrayList<TimetableE>();
	@Property
	private TimetableE saturday;
	@Property
	private List<TimetableE> saturdayTrainings=new ArrayList<TimetableE>();
	@Property
	private TimetableE sunday;
	@Property
	private List<TimetableE> sundayTrainings=new ArrayList<TimetableE>();
	
	@Inject
	private Authenticator autenticator;

	void onActivate(){
		mondayTrainings=new ArrayList<TimetableE>();
		tuesdayTrainings=new ArrayList<TimetableE>();
		wednesdayTrainings=new ArrayList<TimetableE>();
		thursdayTrainings=new ArrayList<TimetableE>();
		fridayTrainings=new ArrayList<TimetableE>();
		saturdayTrainings=new ArrayList<TimetableE>();
		sundayTrainings=new ArrayList<TimetableE>();
		
		List<TimetableE>list =timetableDAO.findAll();
		if(list!=null){
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getDay().equals(Day.MONDAY)){
				mondayTrainings.add(list.get(i));
			}else if(list.get(i).getDay().equals(Day.TUESDAY)){
				tuesdayTrainings.add(list.get(i));
			}else if(list.get(i).getDay().equals(Day.WENESDAY)){
				wednesdayTrainings.add(list.get(i));
			}else if(list.get(i).getDay().equals(Day.THURSDAY)){
				thursdayTrainings.add(list.get(i));
			}else if(list.get(i).getDay().equals(Day.FRIDAY)){
				fridayTrainings.add(list.get(i));
			}else if(list.get(i).getDay().equals(Day.SATURDAY)){
				saturdayTrainings.add(list.get(i));
			}else if(list.get(i).getDay().equals(Day.SUNDAY)){
				sundayTrainings.add(list.get(i));
			}
		}
		}
	}
	
	void onActionFromClear() {
		timetableDAO.deleteAll();
	}
	
	public User getUserF() {
		if (autenticator.isLoggedIn()) {
			if (!autenticator.getLoggedUser().getType().equalsIgnoreCase("user")) {
				return autenticator.getLoggedUser();
			}
		}
		return null;
	}
}
