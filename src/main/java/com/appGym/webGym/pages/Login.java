package com.appGym.webGym.pages;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Log;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.appGym.webGym.annotations.AnonymousAccess;
import com.appGym.webGym.security.AuthenticationException;
import com.appGym.webGym.services.Authenticator;

@Import(stylesheet = { "context:layout/bootstrap/css/bootstrap.css"})
@AnonymousAccess
public class Login {

	@Property
	private String username;

	@Property
	private String password;

	@Inject
	private Authenticator authenticator;

	@Component
	private Form loginForm;

	@Inject
	private Messages messages;

	@Log
	public Object onSubmitFromLoginForm() {
		try {
			authenticator.login(username, password);
		} catch (AuthenticationException ex) {
			loginForm.recordError(messages.get("error.login"));
			return null;
		}

		return Index.class;
	}
}
