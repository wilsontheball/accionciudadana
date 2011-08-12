package ar.com.thinksoft.ac.webac.web.login;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import ar.com.thinksoft.ac.intac.IPermiso;
import ar.com.thinksoft.ac.webac.web.base.BasePage;

public class LoginPage extends BasePage {

	@Override
	public IPermiso getPermisoNecesario() {
		return new LoginPermiso();
	}

	public LoginPage(final PageParameters parameters) {

		add(CSSPackageResource.getHeaderContribution(LoginPage.class,
				"LoginPage.css"));
		add(new FeedbackPanel("feedback"));
		add(new LoginForm("loginForm"));

	}

}
