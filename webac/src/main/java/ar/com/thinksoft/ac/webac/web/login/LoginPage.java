package ar.com.thinksoft.ac.webac.web.login;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;

import ar.com.thinksoft.ac.intac.IPermiso;
import ar.com.thinksoft.ac.webac.web.base.BasePage;

public class LoginPage extends BasePage {

	@Override
	public IPermiso getPermisoNecesario() {
		return new LoginPermiso();
	}

	public LoginPage(final PageParameters parameters) {
		add(CSSPackageResource.getHeaderContribution(LoginPage.class,"Login.css"));
		add( new Button("accederButton"));
		add( new Button("registrarButton"));
		
		
	}
	


}
