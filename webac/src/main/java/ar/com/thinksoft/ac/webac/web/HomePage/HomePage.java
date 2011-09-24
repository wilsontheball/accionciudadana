package ar.com.thinksoft.ac.webac.web.HomePage;

import org.apache.wicket.PageParameters;
import ar.com.thinksoft.ac.webac.seguridad.Permiso;
import ar.com.thinksoft.ac.webac.web.HomePage.Administrativo.HomePageAdministrativo;
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
		//TODO
		//setResponsePage(HomePageCiudadano.class);
		
		//SI ES ADMIN
		//TODO: if(Context.getInstance().getUsuario().equals("administrator"))
			setResponsePage(HomePageAdministrativo.class);
	}

	
}
