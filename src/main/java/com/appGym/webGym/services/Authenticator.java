package com.appGym.webGym.services;

import com.appGym.webGym.entities.User;
import com.appGym.webGym.security.AuthenticationException;

public interface Authenticator {

	public User getLoggedUser();

	public boolean isLoggedIn();

	public void login(String username, String password)
			throws AuthenticationException;

	public void logout();
}
