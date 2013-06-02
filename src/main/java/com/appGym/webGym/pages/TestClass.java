package com.appGym.webGym.pages;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.PageActivationContext;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.services.PropertyAccess;

import com.appGym.webGym.annotations.AnonymousAccess;
import com.appGym.webGym.dao.EvidenceExerciseDAO;
import com.appGym.webGym.dao.ExerciseDAO;
import com.appGym.webGym.entities.Day;
import com.appGym.webGym.entities.EvidenceExercise;
import com.appGym.webGym.entities.Exercise;
import com.appGym.webGym.entities.Training;
import com.appGym.webGym.entities.User;
import com.appGym.webGym.model.user.GenericSelectModel;

@AnonymousAccess
public class TestClass {

	@PageActivationContext
	  @Property
	  private Exercise  person;

	  @Property
	  private EvidenceExercise phone;
	  @Property
	  List< Exercise> list=  exercises();
	  
	  @Inject
	  private ExerciseDAO exerciseDAO;

//	  @Inject
//	  private Session session;
	  
	  public List<Exercise> exercises(){
		 return exerciseDAO.findAll();
	  }

	  @CommitAfter
	  public Object onSuccess()
	  {
	    return Index.class;
	  }

	  @CommitAfter
	  Object onAddRowFromPhones()
	  {
	    EvidenceExercise phone = new EvidenceExercise();

	    return phone;
	  }

	  @CommitAfter
	  void onRemoveRowFromPhones(Training phone)
	  {
//	    session.delete(phone);
	  }
}
