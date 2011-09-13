package ar.com.thinksoft.ac.webac.web.base;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.JavascriptPackageResource;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.IPageLink;

import ar.com.thinksoft.ac.webac.HomePage;
import ar.com.thinksoft.ac.webac.seguridad.Permitible;
import ar.com.thinksoft.ac.webac.web.Context;
import ar.com.thinksoft.ac.webac.web.login.LoginPage;
import ar.com.thinksoft.ac.webac.web.logout.LogoutPage;
import ar.com.thinksoft.ac.webac.web.reclamo.altaReclamo.AltaReclamoPage;
import ar.com.thinksoft.ac.webac.web.reclamo.busquedaReclamo.BusquedaReclamoPage;
import ar.com.thinksoft.ac.webac.web.usuario.alta.UsuarioPage;
import ar.com.thinksoft.ac.webac.web.logout.LogoutPage;

public abstract class BasePage extends Permitible {

	public BasePage() {

		/*if (!Context.getInstance().isUserSignedIn()) {
			System.out.println(Context.getInstance().getUsuario());
			setResponsePage(LoginPage.class);
		} else {
*/
			add(CSSPackageResource.getHeaderContribution(BasePage.class,
					"../css/BasePage.css"));
			add(JavascriptPackageResource.getHeaderContribution(BasePage.class,
					"../js/jquery.js"));
			add(JavascriptPackageResource.getHeaderContribution(BasePage.class,
			"../js/basePage.js"));
			this.appendLinks();

			add(this.userNameComponent());

			BookmarkablePageLink<IPageLink> logout = new BookmarkablePageLink<IPageLink>(
					"logout", LogoutPage.class);
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
	//	}

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
		add(new BookmarkablePageLink<IPageLink>("homeLink", HomePage.class));
		add(new BookmarkablePageLink<IPageLink>("altaReclamoLink",
				AltaReclamoPage.class));
		add(new BookmarkablePageLink<IPageLink>("busquedaReclamoLink",
				BusquedaReclamoPage.class));
		add(new BookmarkablePageLink<IPageLink>("usuariosLink",
				UsuarioPage.class));

	}

}
