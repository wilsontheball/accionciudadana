package ar.com.thinksoft.ac.webac;


import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.markup.html.WebPage;

import ar.com.thinksoft.ac.webac.web.login.LoginPage;

/**
 * Application object for your web application. If you want to run this
 * application without deploying, run the Start class.
 * 
 * @see ar.com.thinksoft.ac.webac.Start#main(String[])
 */
public class WicketApplication extends AuthenticatedWebApplication{
	/**
	 * Constructor
	 */
	public WicketApplication() {
	}

	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	public Class<LoginPage> getHomePage() {
		return LoginPage.class;
	}

	@Override
	protected void init() {
		getResourceSettings()
				.setAddLastModifiedTimeToResourceReferenceUrl(true);
		
		getDebugSettings().setAjaxDebugModeEnabled(false);
		
	}

	@Override
	protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
		return AccionCiudadanaSession.class;
	}

	@Override
	protected Class<? extends WebPage> getSignInPageClass() {
		return LoginPage.class;
	}

}
