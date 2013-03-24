package com.appGym.webGym.security;

import java.io.IOException;

import org.apache.tapestry5.Link;
import org.apache.tapestry5.runtime.Component;
import org.apache.tapestry5.services.ComponentEventRequestParameters;
import org.apache.tapestry5.services.ComponentRequestFilter;
import org.apache.tapestry5.services.ComponentRequestHandler;
import org.apache.tapestry5.services.ComponentSource;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.apache.tapestry5.services.PageRenderRequestParameters;
import org.apache.tapestry5.services.Response;
import org.hibernate.annotations.Index;

import com.appGym.webGym.annotations.AnonymousAccess;
import com.appGym.webGym.annotations.UserAccess;
import com.appGym.webGym.pages.Login;
import com.appGym.webGym.services.Authenticator;



public class AuthenticationFilter implements ComponentRequestFilter {

	private final PageRenderLinkSource renderLinkSource;

	private final ComponentSource componentSource;

	private final Response response;

	private final Authenticator authenticator;

	private String IndexPage = Index.class.getSimpleName();

	private String loginPage = Login.class.getSimpleName();

	public AuthenticationFilter(PageRenderLinkSource renderLinkSource,
			ComponentSource componentSource, Response response,
			Authenticator authenticator) {
		this.renderLinkSource = renderLinkSource;
		this.componentSource = componentSource;
		this.response = response;
		this.authenticator = authenticator;
	}

	public void handleComponentEvent(
			ComponentEventRequestParameters parameters,
			ComponentRequestHandler handler) throws IOException {

		if (dispatchedToLoginPage(parameters.getActivePageName())) {
			return;
		}

		handler.handleComponentEvent(parameters);

	}

	public void handlePageRender(PageRenderRequestParameters parameters,
			ComponentRequestHandler handler) throws IOException {

		if (dispatchedToLoginPage(parameters.getLogicalPageName())) {
			return;
		}

		handler.handlePageRender(parameters);
	}

	private boolean dispatchedToLoginPage(String pageName) throws IOException {
		Component page = componentSource.getPage(pageName);
		Link link = renderLinkSource.createPageRenderLink("Index");

		if (authenticator.isLoggedIn()) {
			// Logged user should not go back to Login
			if (loginPage.equalsIgnoreCase(pageName)) {
				response.sendRedirect(link);
				return true;
			}

			if (authenticator.getLoggedUser().getType().equalsIgnoreCase("admin")) {
				return false;
			}

			if (authenticator.getLoggedUser().getType().equalsIgnoreCase("user")) {
				if (page.getClass().isAnnotationPresent(UserAccess.class)) {
					return false;
				} else {
					response.sendRedirect(link);
					return true;
				}
			}

			return false;

		}

		if (page.getClass().isAnnotationPresent(AnonymousAccess.class)) {
			return false;
		}
		
		Link login = renderLinkSource.createPageRenderLink("Login");
		response.sendRedirect(login);
		return true;

	}
}
