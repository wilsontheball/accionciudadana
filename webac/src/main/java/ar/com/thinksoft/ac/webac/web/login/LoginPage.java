package ar.com.thinksoft.ac.webac.web.login;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.IPageLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

import ar.com.thinksoft.ac.intac.IPermiso;
import ar.com.thinksoft.ac.webac.HomePage;
import ar.com.thinksoft.ac.webac.web.Context;
import ar.com.thinksoft.ac.webac.web.base.BasePage;
import ar.com.thinksoft.ac.webac.web.registro.RegistroPage;

public class LoginPage extends BasePage {

	@Override
	public IPermiso getPermisoNecesario() {
		return new LoginPermiso();
	}

	public LoginPage(final PageParameters parameters) {

		add(CSSPackageResource.getHeaderContribution(LoginPage.class,
				"../css/LoginPage.css"));
		if (Context.getInstance().getUsuario() != null)
			setResponsePage(HomePage.class);
		else {
			add(new FeedbackPanel("feedback"));
			add(new LoginForm("loginForm"));
			add(new BookmarkablePageLink<IPageLink>("registerLink",RegistroPage.class));
		}

	}

}