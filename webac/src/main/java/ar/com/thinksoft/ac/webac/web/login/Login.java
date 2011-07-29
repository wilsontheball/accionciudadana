package ar.com.thinksoft.ac.webac.web.login;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;

import ar.com.thinksoft.ac.intac.IPermiso;
import ar.com.thinksoft.ac.webac.web.base.BasePage;

public class Login extends BasePage {

	@Override
	public IPermiso getPermisoNecesario() {
		return new LoginPermiso();
	}

	public Login(final PageParameters parameters) {
		add(CSSPackageResource.getHeaderContribution(Login.class,"Login.css"));
		add( new Button("accederButton"));
		add( new Button("registrarButton"));
		
		
	}
	


}
