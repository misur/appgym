package com.appGym.webGym.pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.StreamResponse;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.services.PropertyAccess;
import org.apache.tapestry5.services.BeanModelSource;
import org.apache.tapestry5.services.Request;
import org.got5.tapestry5.jquery.components.InPlaceEditor;

import com.appGym.webGym.annotations.AdminAccess;
import com.appGym.webGym.annotations.AnonymousAccess;
import com.appGym.webGym.annotations.UserAccess;
import com.appGym.webGym.dao.MembershipDAO;
import com.appGym.webGym.dao.TrainingDAO;
import com.appGym.webGym.dao.UserDAO;
import com.appGym.webGym.entities.Membership;
import com.appGym.webGym.entities.Training;
import com.appGym.webGym.entities.User;
import com.appGym.webGym.model.user.GenericSelectModel;
import com.appGym.webGym.reports.ExportFormat;
import com.appGym.webGym.reports.ListReports;
import com.appGym.webGym.reports.RunReport;

@UserAccess
@AdminAccess
@AnonymousAccess
public class ReviewUsers {
	@Property
	private User user;

	@Property
	private int currentIndex;

	@Property
	@Persist
	private List<User> users;

	@Inject
	private BeanModelSource _beanModelSource;

	@Inject
	private ComponentResources _componentResources;

	@Inject
	private Request request;

	@Inject
	private UserDAO dao;

//	@Property
//	@Persist
//	private String selectedTypes;
//	private GenericSelectModel<String> types;
	
	@Property
	private Training training;
	
	@Property
	private List<Training> trainingList =getTrainings();
	
	@Inject
	private MembershipDAO membershipDAO;
	
	@Property
	private Membership membership;
	
	@Property
	private List<Membership>memerships = getMemberships() ; 
	
	@Inject
	private TrainingDAO trainingDAO;

	@Inject
	private PropertyAccess access;
	
	
	public List<Training> getTrainings(){
		
		return trainingDAO.findAll();
	}
	
	public List<Membership> getMemberships() {
		return  membershipDAO.findAll();
	}

	public GenericSelectModel<String> getTypes() {
		List<String> arr =  Arrays.asList("admin","user","trainer");
		return new GenericSelectModel<String>(arr, String.class, null, null, access);
	}
	@OnEvent(component = "runReport")
	public StreamResponse onRunReport() {
		return RunReport.getRunReport(ListReports.ALL_USERS,
				ExportFormat.PDF, null, "All_USERS");
	}
	
	@OnEvent(component = "runReportTrainings")
	public StreamResponse onRunReportTrainings() {
		return RunReport.getRunReport(ListReports.ALL_TRAININGS,
				ExportFormat.PDF, null, "ALL_TRAININGS");
	}

	
//	public GenericSelectModel<User> getUsers() {
//		return new GenericSelectModel<User>(userDAO.findAll(), User.class, "fullname", "id", access);
	
	void setupRender() {
		// users = createUsers(50);
		users = dao.findAll();
	}

	public BeanModel getMyModel() {
		BeanModel myModel = _beanModelSource.createDisplayModel(User.class,
				_componentResources.getMessages());
		myModel.add("action", null);
		myModel.include("name", "surname", "username", "email", "dateOfBirth",
				"type", "action");
		myModel.get("name").sortable(false);
		myModel.get("email").sortable(false);
		myModel.get("dateOfBirth").sortable(false);
		myModel.get("username").label("username");
		myModel.get("surname").sortable(false);
		myModel.get("type").label("type");
		return myModel;
	}
	
	public BeanModel getMyModel1() {
		BeanModel myModel = _beanModelSource.createDisplayModel(Training.class,
				_componentResources.getMessages());
		myModel.add("action", null);
		myModel.include("name","type","rating","price","action");
		return myModel;
	}
	
	public BeanModel getMyModel2() {
		BeanModel myModel = _beanModelSource.createDisplayModel(Membership.class,
				_componentResources.getMessages());
		return myModel;
		
	}

	private User createUser(int i) {
		User u = new User();
		// u.setAge(i);
		u.setName("Humpty" + i + 10);
		u.setSurname("Dumpty" + i + 200);
		return u;
	}

	private List<User> createUsers(int number) {
		List<User> users = new ArrayList<User>();

		for (int i = 0; i < number; i++) {
			users.add(createUser(i));
		}

		return users;
	}

	@OnEvent(component = "surname", value = InPlaceEditor.SAVE_EVENT)
	void setSurname(Long id, String value) {
		User user = (User) users.get(id.intValue());
		user.setSurname(value);
		dao.update(user);
	}

	@OnEvent(component = "username", value = InPlaceEditor.SAVE_EVENT)
	void setUsername(Long id, String value) {
		User user = (User) users.get(id.intValue());
		user.setUsername(value);
		dao.update(user);
	}

	@OnEvent(component = "email", value = InPlaceEditor.SAVE_EVENT)
	void setEmail(Long id, String value) {
		User user = (User) users.get(id.intValue());
		user.setEmail(value);
		dao.update(user);
	}

	@OnEvent(component = "dateOfBirth", value = InPlaceEditor.SAVE_EVENT)
	void setDateOfBirth(Long id, Date value) {
		User user = (User) users.get(id.intValue());
		user.setDateOfBirth(value);
		dao.update(user);
	}

	@OnEvent(component = "name", value = InPlaceEditor.SAVE_EVENT)
	void setName(Long id, String value) {
		User user = (User) users.get(id.intValue());
		user.setName(value);
		dao.update(user);
	}

	@OnEvent(component = "type", value = InPlaceEditor.SAVE_EVENT)
	void setType(Long id, String value) {
		User user = (User) users.get(id.intValue());
		user.setType(value);
		dao.update(user);
	}

	@CommitAfter
	Object onActionFromDelete(Long userId) {
		dao.deleteByID(userId);
		return null;
	}
	
	@CommitAfter
	Object onActionFromDeleteTraining(Long Id) {
		trainingDAO.deleteByID(Id);
		return null;
	}
	
	
	
}