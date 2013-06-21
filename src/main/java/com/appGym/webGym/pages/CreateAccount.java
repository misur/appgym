package com.appGym.webGym.pages;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.services.PropertyAccess;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;
import org.apache.tapestry5.upload.services.UploadedFile;
import org.apache.tapestry5.util.EnumSelectModel;
import org.got5.tapestry5.jquery.JQueryEventConstants;
import org.got5.tapestry5.jquery.components.AjaxUpload;

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
	private String phone;
	@Property
	private String image;
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
	private Gender selectedGender;
	private GenericSelectModel<Gender> gender;

	@Inject
	private PropertyAccess access;

	@Persist(PersistenceConstants.FLASH)
	private String message;
	@Property
	private List<UploadedFile> uploadedFiles= new ArrayList<UploadedFile>();
	@InjectComponent
	private Zone uploadResult;
	@Inject
	private ComponentResources resources;
	@Inject
	private AjaxResponseRenderer ajaxResponseRenderer;

	@Property
	private User user = new User();
	
	  @Property
	    private String dateInFormatStr = "dd/MM/yyyy";

	public GenericSelectModel<Gender> getGenders() {
		List<Gender> arr = Arrays.asList(Gender.MALE, Gender.FEMALE);
		return new GenericSelectModel<Gender>(arr, Gender.class, null, null,
				access);
	}

	Object onSubmitFromForm() {
		user.setUsername(this.username);
		user.setPassword(MD5.md5(this.password));
		user.setName(this.name);
		user.setSurname(this.surname);
		user.setType("user");
		user.setGender(selectedGender.toString());
		user.setEmail(this.email);
		user.setDateOfBirth(dateOfBirth);
		user.setPhone(phone);
		System.out.println(user.toString());
		if(user.getUsername()==null||user.getPassword()==null||user.getGender()==null||user.getImage()==null||user.getPassword().length()<5){
			return null;
		}
		userDao.save(user);
		user = new User();
		return Index.class;
	}

	void onActivate() {
		if (uploadedFiles != null)
			uploadedFiles = new ArrayList<UploadedFile>();
	}
	

	@OnEvent(component = "uploadImage1", value = JQueryEventConstants.AJAX_UPLOAD)
	void onImageUpload(UploadedFile uploadedFile) {
		if (uploadedFile != null) {
			Date d = new Date();
			this.uploadedFiles.add(uploadedFile);
			// scoutP.setImage(uploadedFile.getFilePath());
			String dateString = d.getYear() + d.getMonth() + d.getHours()
					+ d.getMinutes() + d.getSeconds() + d.getTime()
					+ uploadedFile.getFileName();
			String s = "./src/main/webapp/layout/images/profile/" + dateString;
			File f2 = new File(s);
			uploadedFile.write(f2);
			this.image = "." + "/layout/images/profile/" + dateString;
			user.setImage(image);

		}
		final JSONObject result = new JSONObject();
		final JSONObject params = new JSONObject().put(
				"url",
				resources.createEventLink("myCustomEvent", "NON_XHR_UPLOAD")
						.toURI()).put("zoneId", "uploadResult");
		result.put(AjaxUpload.UPDATE_ZONE_CALLBACK, params);
		message = "This upload was: AJAX_UPLOAD";
		ajaxResponseRenderer.addRender("uploadResult", uploadResult);
	}

	@OnEvent(component = "uploadImage1", value = JQueryEventConstants.NON_XHR_UPLOAD)
	Object onNonXHRImageUpload(UploadedFile uploadedFile) {
		System.out
				.println("2-/***************************************************////*/**/*/*/");
		if (uploadedFile != null) {
			Date d = new Date();
			this.uploadedFiles.add(uploadedFile);
			// scoutP.setImage(uploadedFile.getFilePath());
			System.out.println(uploadedFile.getFilePath() + "ETO TU JE");

			String dateString = d.getYear() + d.getMonth() + d.getHours()
					+ d.getMinutes() + d.getSeconds() + d.getTime()
					+ uploadedFile.getFileName();

			String s = "./src/main/webapp/layout/images" + dateString;

			File f2 = new File(s);

			uploadedFile.write(f2);

			this.image = "." + "/layout/images" + dateString;
			user.setImage(image);
		}
		final JSONObject result = new JSONObject();
		final JSONObject params = new JSONObject().put(
				"url",
				resources.createEventLink("myCustomEvent", "NON_XHR_UPLOAD")
						.toURI()).put("zoneId", "uploadResult");
		result.put(AjaxUpload.UPDATE_ZONE_CALLBACK, params);
		return result;
	}

	@OnEvent(value = "myCustomEvent")
	void onMyCustomEvent(final String someParam) {

		message = "This upload was: " + someParam;

		ajaxResponseRenderer.addRender("uploadResult", uploadResult);
	}

	void onUploadException(FileUploadException ex) {

		message = "Upload exception: " + ex.getMessage();

		ajaxResponseRenderer.addRender("uploadResult", uploadResult);
	}

	public String getMessage() {

		return message;
	}

}
