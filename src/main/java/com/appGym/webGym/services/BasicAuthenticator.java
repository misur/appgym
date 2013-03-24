package com.appGym.webGym.services;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.Session;

import com.appGym.webGym.dao.UserDAO;
import com.appGym.webGym.entities.User;
import com.appGym.webGym.model.user.MD5;
import com.appGym.webGym.security.AuthenticationException;


public class BasicAuthenticator implements Authenticator {

	public static final String AUTH_TOKEN = "authToken";

	@Inject
	private UserDAO userDAO;

	@Inject
	private Request request;

	public void login(String username, String password)
			throws AuthenticationException {
		System.out.println(username+" "+password);
		password = MD5.md5(password);
		User user = userDAO.findByUserNameAndPassword(username, password);
		System.out.println("%%%%%%"+user+"$$$$$$$$$$$ ");
		
		if (user == null) {
			throw new AuthenticationException("The user doesn't exist");
		}

		request.getSession(true).setAttribute(AUTH_TOKEN, user);
	}

	public boolean isLoggedIn() {
		Session session = request.getSession(false);
		if (session != null) {
			return session.getAttribute(AUTH_TOKEN) != null;
		}
		return false;
	}

	public void logout() {
		Session session = request.getSession(false);
		if (session != null) {
			session.setAttribute(AUTH_TOKEN, null);
			session.invalidate();
		}
	}

	public User getLoggedUser() {
		User user = null;

		if (isLoggedIn()) {
			user = (User) request.getSession(true).getAttribute(AUTH_TOKEN);
		} else {
			throw new IllegalStateException("The user is not logged ! ");
		}
		return user;
	}

}
