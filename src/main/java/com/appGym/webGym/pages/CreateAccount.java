package com.appGym.webGym.pages;

import java.util.Date;

import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.appGym.webGym.annotations.AdminAccess;
import com.appGym.webGym.annotations.AnonymousAccess;
import com.appGym.webGym.annotations.UserAccess;
import com.appGym.webGym.dao.UserDAO;
import com.appGym.webGym.entities.Gender;
import com.appGym.webGym.entities.User;
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
	private Gender gender;
	@Property
	private String email;
	@Property
	private Date dateOfBirth;
	@Inject
	private UserDAO userDao;
	
	private Class nextPage;
	
	Object onSubmitFromForm(){
		User user = new User();
		user.setUsername(this.username);
		user.setPassword( MD5.md5(this.password));
//		user.setPassword( this.password);
		user.setName(this.name);
		user.setSurname(this.surname);
		user.setType("user");
//		user.setGender(this.gender);
		user.setEmail(this.email);
		user.setDateOfBirth(dateOfBirth);
		System.out.println(user.toString());
		userDao.save(user);
		return null;
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
