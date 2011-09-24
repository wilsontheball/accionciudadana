package ar.com.thinksoft.ac.webac.web.HomePage;

import org.apache.wicket.PageParameters;
import ar.com.thinksoft.ac.webac.seguridad.Permiso;
import ar.com.thinksoft.ac.webac.web.HomePage.Ciudadano.HomePageCiudadano;
import ar.com.thinksoft.ac.webac.web.base.BasePage;

/**
 * Homepage
 */
public class HomePage extends BasePage {

	private static final long serialVersionUID = 1L;

	// ****************** SEGURIDAD *******************
	@Override
	public Permiso getPermisoNecesario() {
		return new HomePagePermiso();
	}

	public HomePage(final PageParameters parameters) {
		
		//SI es ciudadano
		setResponsePage(HomePageCiudadano.class);
		
		//SI ES ADMIN
		//setResponsePage(HomePageAdministrativo.class);
	}

	
}
