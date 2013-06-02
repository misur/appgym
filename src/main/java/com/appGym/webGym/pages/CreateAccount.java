package com.appGym.webGym.pages;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.services.PropertyAccess;

import com.appGym.webGym.annotations.AdminAccess;
import com.appGym.webGym.annotations.AnonymousAccess;
import com.appGym.webGym.annotations.UserAccess;
import com.appGym.webGym.dao.UserDAO;
import com.appGym.webGym.entities.Gender;
import com.appGym.webGym.entities.User;
import com.appGym.webGym.model.user.GenericSelectModel;
import com.appGym.webGym.model.user.MD5;
@UserAccess
@AdminAccess
@AnonymousAccess
public class CreateAccount {

	@Property
	private String username;
	@Property
	private String password;
	@Property
	private String name;
	@Property
	private String surname;
	@Property
	private boolean male;
	@Property
	private boolean female;
	@Property
	private String email;
	@Property
	private Date dateOfBirth;
	@Inject
	private UserDAO userDao;
	
	private Class nextPage;
	
	@Property
	@Persist
	private Gender selectedGender;
	private GenericSelectModel<Gender> gender;

	@Inject
	private PropertyAccess access;

	public GenericSelectModel<Gender> getGenders() {
		List<Gender> arr = Arrays.asList(Gender.MALE,Gender.FEMALE);
		return new GenericSelectModel<Gender>(arr, Gender.class, null, null,
				access);
	}
	
	Object onSubmitFromForm(){
		User user = new User();
		user.setUsername(this.username);
		user.setPassword( MD5.md5(this.password));
		user.setName(this.name);
		user.setSurname(this.surname);
		user.setType("user");
		user.setGender(selectedGender.toString());
		user.setEmail(this.email);
		user.setDateOfBirth(dateOfBirth);
		System.out.println(user.toString());
		userDao.save(user);
		return Index.class;
	}


//	@OnEvent(component = "submitButton")
//	void onSubmitButton() {
//		System.out.println("Radi111");
//		
//
//
//	}
//	// void onSelectedFromSend() {
//	// System.out.println("TEST" + username);
//	// }

}
