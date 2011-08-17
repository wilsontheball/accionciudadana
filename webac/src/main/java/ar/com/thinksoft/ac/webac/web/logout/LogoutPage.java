package ar.com.thinksoft.ac.webac.web.logout;

import org.apache.wicket.PageParameters;

import ar.com.thinksoft.ac.intac.IPermiso;
import ar.com.thinksoft.ac.webac.HomePage;
import ar.com.thinksoft.ac.webac.web.base.BasePage;

public class LogoutPage extends BasePage{

	@Override
	public IPermiso getPermisoNecesario() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/*
	 * CONSTRUCTOR
	 */
	
	
	public LogoutPage(final PageParameters pageParameters) {
		getSession().invalidateNow();
		setResponsePage(HomePage.class);
	}

}
