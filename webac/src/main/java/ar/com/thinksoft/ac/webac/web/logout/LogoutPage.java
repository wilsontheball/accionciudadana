package ar.com.thinksoft.ac.webac.web.logout;

import org.apache.wicket.PageParameters;

import ar.com.thinksoft.ac.webac.web.Context;
import ar.com.thinksoft.ac.webac.web.base.BasePage;
import ar.com.thinksoft.ac.webac.web.login.LoginPage;

public class LogoutPage extends BasePage{

	
	public LogoutPage(final PageParameters pageParameters) {
		Context.getInstance().invalidate();
		getSession().invalidateNow();
		setResponsePage(LoginPage.class);
	}

}
