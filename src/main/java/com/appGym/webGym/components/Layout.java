package com.appGym.webGym.components;

import javax.swing.text.StyledEditorKit.BoldAction;

import org.apache.tapestry5.*;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.ioc.annotations.*;
import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.SymbolConstants;
import org.got5.tapestry5.jquery.components.InPlaceEditor;

import com.appGym.webGym.entities.User;
import com.appGym.webGym.services.Authenticator;
import com.appGym.webGym.services.BasicAuthenticator;

/**
 * Layout component for pages of application appGym.
 */
@Import(stylesheet = { "context:layout/css/reset.css",
		"context:layout/css/style.css", "context:layout/css/grid_12.css",
		"context:layout/css/slider.css", "context:layout/css/demo.css" }, library = {
		"context:layout/js/jquery-1.7.min.js",
		"context:layout/js/jquery.easing.1.3.js",
		"context:layout/js/uCarousel.js", "context:layout/js/tms-0.3.js",
		"context:layout/js/tms_presets.js", "context:layout/js/cufon-yui.js",
		"context:layout/js/Asap_400.font.js",
		"context:layout/js/Coolvetica_400.font.js",
		"context:layout/js/Kozuka_M_500.font.js",
		"context:layout/js/cufon-replace.js",
		"context:layout/js/FF-cash.js" })
public class Layout {
	/**
	 * The page title, for the <title> element and the <h1>element.
	 */
	@Property
	@Parameter(required = true, defaultPrefix = BindingConstants.LITERAL)
	private String title;

	@Property
	private String pageName;

	@Property
	@Parameter(defaultPrefix = BindingConstants.LITERAL)
	private String sidebarTitle;

	@Property
	@Parameter(defaultPrefix = BindingConstants.LITERAL)
	private Block sidebar;

	@Inject
	private ComponentResources resources;

	@Property
	@Inject
	@Symbol(SymbolConstants.APPLICATION_VERSION)
	private String appVersion;
	@Inject
	private Authenticator autenticator;

	@Persist
	@Property
	private boolean logged;

	void onActivate() {
		logged = autenticator.isLoggedIn();
	}

	@OnEvent(component = "logout")
	void setLogout() {
		autenticator.logout();
	}

	public String getClassForPageName() {
		return resources.getPageName().equalsIgnoreCase(pageName) ? "current_page_item"
				: null;
	}

	public String[] getPageNames() {
		return new String[] { "Index", "About", "Contact" };
	}

	public boolean checkLogged() {
		System.out.println("------------------------"
				+ autenticator.isLoggedIn());
		return autenticator.isLoggedIn();
	}

	public User getUser() {
		if (autenticator.isLoggedIn()) {
			return autenticator.getLoggedUser();
		}
		return null;
	}

	public User getAdmin() {
		if (autenticator.getLoggedUser().getType().equalsIgnoreCase("admin")
				|| autenticator.getLoggedUser().getType()
						.equalsIgnoreCase("trainer")) {
			return autenticator.getLoggedUser();
		}
		return null;
	}
}
