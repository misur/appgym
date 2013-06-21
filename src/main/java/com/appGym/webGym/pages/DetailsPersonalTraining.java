package com.appGym.webGym.pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.annotation.PropertyKey;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.StreamResponse;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.corelib.components.Grid;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.services.PropertyAccess;
import org.apache.tapestry5.services.BeanModelSource;

import com.appGym.webGym.annotations.AdminAccess;
import com.appGym.webGym.annotations.TrainerAccess;
import com.appGym.webGym.annotations.UserAccess;
import com.appGym.webGym.dao.EvidenceExerciseDAO;
import com.appGym.webGym.dao.ExerciseDAO;
import com.appGym.webGym.dao.MyTrainingsDAO;
import com.appGym.webGym.dao.TrainingDAO;
import com.appGym.webGym.dao.UserDAO;
import com.appGym.webGym.entities.Day;
import com.appGym.webGym.entities.EvidenceExercise;
import com.appGym.webGym.entities.Exercise;
import com.appGym.webGym.entities.MyTrainings;
import com.appGym.webGym.entities.Training;
import com.appGym.webGym.entities.User;
import com.appGym.webGym.model.user.GenericSelectModel;
import com.appGym.webGym.reports.ExportFormat;
import com.appGym.webGym.reports.ListReports;
import com.appGym.webGym.reports.RunReport;
import com.appGym.webGym.services.Authenticator;

@AdminAccess
@TrainerAccess
@UserAccess
public class DetailsPersonalTraining {
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

	@Property
	private EvidenceExercise evidenceExercise;
	@Inject
	private ExerciseDAO exerciseDAO;
	@Inject
	private EvidenceExerciseDAO evidenceExerciseDAO;

	@Property
	private List<EvidenceExercise> list;
	@Property
	private User user;
	@Property
	private MyTrainings myTrainings;
	@Persist
	@Property
	private List<MyTrainings> users;

	@InjectComponent
	private Zone zone;

	@Inject
	private PropertyAccess access;

	void onActivate(Long id) {

		training = trainingDAO.findByID(id);
		users = getAllUserFromTraining();
		trainer = userDAO.findByID(training.getTrainer().getId());
		list = new ArrayList<EvidenceExercise>();
		list = findExercise(training);
	}

	Object onSubmitFromForm() {
		MyTrainings myTrainings = new MyTrainings();
		myTrainings.setTraining(training);
		myTrainings.setUser(autenticator.getLoggedUser());
		myTrainingsDAO.save(myTrainings);
		return Trainings.class;
	}

	public User check() {

		return null;
	}

	public User getUser1() {
		if (autenticator.isLoggedIn()) {
			if (myTrainingsDAO.findTrainingByUserIDAndTrainingID(
					training.getId(), autenticator.getLoggedUser()) != null
					|| autenticator.getLoggedUser().getType().equals("trainer")) {
				return autenticator.getLoggedUser();
			}
		}
		return null;
	}

	public User getTrainerV() {
		if (autenticator.getLoggedUser().getType().equals("trainer")) {
			return autenticator.getLoggedUser();
		}
		return null;
	}

	public List<EvidenceExercise> findExercise(Training training) {
		List<EvidenceExercise> list = evidenceExerciseDAO.findAll();
		List<EvidenceExercise> list2 = new ArrayList<EvidenceExercise>();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getTraining().getId().equals(training.getId())) {
				list2.add(list.get(i));
			}
		}
		return list2;
	}

	public List<Exercise> exercises() {
		return exerciseDAO.findAll();
	}

	public List<MyTrainings> getAllUserFromTraining() {
		List<MyTrainings> list1 = myTrainingsDAO
				.findAllUserFromTraining(training);
		List<MyTrainings> pom = new ArrayList<MyTrainings>();
		for (int i = 0; i < list1.size(); i++) {
			if (list1.get(i).getTraining().getId().equals(training.getId())) {
				pom.add(list1.get(i));
			}
		}
		return pom;
	}

	@Property
	private int currentIndex;
	@Inject
	private BeanModelSource _beanModelSource;

	@Inject
	private ComponentResources _componentResources;

	public BeanModel getMyModel() {
		BeanModel myModel = _beanModelSource.createDisplayModel(
				MyTrainings.class, _componentResources.getMessages());
		myModel.add("name", null);
		myModel.add("add", null);
		myModel.add("reduce", null);
		myModel.include("name", "presence", "add", "reduce");
		return myModel;
	}

	@OnEvent
	void onActionFromAddPresence(Long id) {
		MyTrainings m = myTrainingsDAO.findByID(id);
		m.setPresence(m.getPresence() + 1);
		myTrainingsDAO.update(m);
		users = getAllUserFromTraining();

	}
	

//	@OnEvent
//	void onActionFromReducePresence(Long id) {
//		MyTrainings m = myTrainingsDAO.findByID(id);
//		m.setPresence(m.getPresence() - 1);
//		myTrainingsDAO.update(m);
//		users = getAllUserFromTraining();
//
//	}

}
