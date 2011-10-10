package ar.com.thinksoft.ac.webac.web.HomePage;

import org.apache.wicket.PageParameters;
import org.apache.wicket.authorization.strategies.role.Roles;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;

import ar.com.thinksoft.ac.webac.AccionCiudadanaSession;
import ar.com.thinksoft.ac.webac.web.HomePage.Administrativo.HomePageAdministrativo;
import ar.com.thinksoft.ac.webac.web.HomePage.Ciudadano.HomePageCiudadano;
import ar.com.thinksoft.ac.webac.web.base.BasePage;

/**
 * Homepage
 */
@AuthorizeInstantiation({"ADMIN","OPERADOR","CIUDADANO"})
public class HomePage extends BasePage {

	private static final long serialVersionUID = 1L;

	public HomePage(final PageParameters parameters) {
		AccionCiudadanaSession session = (AccionCiudadanaSession) getSession();

		if (session.getRoles().hasAnyRole(this.createRolesNeededForAdmin())) {
			setResponsePage(HomePageAdministrativo.class);
			setRedirect(true);
		} else if (session.getRoles().hasAnyRole(
				this.createRolesNeededForUser())) {
			
			setResponsePage(HomePageCiudadano.class);
			setRedirect(true);
			
		}
	}

	public Roles createRolesNeededForAdmin() {
		Roles roles = new Roles();
		roles.add("ADMIN");
		roles.add("OPERADOR");
		return roles;
	}

	public Roles createRolesNeededForUser() {
		Roles roles = new Roles();
		roles.add("CIUDADANO");
		return roles;
	}

}
