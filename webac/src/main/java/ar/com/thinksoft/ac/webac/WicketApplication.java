package ar.com.thinksoft.ac.webac;

import java.util.Set;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.settings.IResourceSettings;

import ar.com.thinksoft.ac.webac.web.login.LoginPage;

/**
 * Application object for your web application. If you want to run this
 * application without deploying, run the Start class.
 * 
 * @see ar.com.thinksoft.ac.webac.Start#main(String[])
 */
public class WicketApplication extends WebApplication {
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
		
	}

}
