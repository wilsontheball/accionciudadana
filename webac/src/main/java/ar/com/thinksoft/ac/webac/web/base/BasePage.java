package ar.com.thinksoft.ac.webac.web.base;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.IPageLink;

import ar.com.thinksoft.ac.webac.HomePage;
import ar.com.thinksoft.ac.webac.seguridad.Permitible;
import ar.com.thinksoft.ac.webac.web.Context;
import ar.com.thinksoft.ac.webac.web.login.LoginPage;

public abstract class BasePage extends Permitible {

	public BasePage() {

		add(CSSPackageResource.getHeaderContribution(BasePage.class,
				"../css/BasePage.css"));
		this.appendLinks();

		add(this.userNameComponent());

		Label logout = new Label("logout", "Logout");
		BookmarkablePageLink<IPageLink> login = new BookmarkablePageLink<IPageLink>(
				"login", LoginPage.class);
		add(logout);
		add(login);

		if (Context.getInstance().isUserSignedIn()) {
			login.setVisible(false);
			logout.setVisible(true);
		} else {
			login.setVisible(true);
			logout.setVisible(false);
		}

	}

	private Component userNameComponent() {
		try {
			String username = Context.getInstance().getUsuario()
					.getNombreUsuario();
			return new Label("username", username);
		} catch (NullPointerException e) {

			return new Label("username", "");
		}
	}

	private void appendLinks() {
		add(new BookmarkablePageLink<IPageLink>("loginLink", LoginPage.class));
		add(new BookmarkablePageLink<IPageLink>("homeLink", HomePage.class));
	}

}
