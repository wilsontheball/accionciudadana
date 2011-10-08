package ar.com.thinksoft.ac.webac.web.HomePage;

import org.apache.wicket.PageParameters;

import ar.com.thinksoft.ac.webac.seguridad.Permiso;
import ar.com.thinksoft.ac.webac.web.Context;
import ar.com.thinksoft.ac.webac.web.HomePage.Administrativo.HomePageAdministrativo;
import ar.com.thinksoft.ac.webac.web.HomePage.Ciudadano.HomePageCiudadano;
import ar.com.thinksoft.ac.webac.web.base.BasePage;
import ar.com.thinksoft.ac.webac.web.login.LoginPage;

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
		if(Context.getInstance().getUsuario() != null){
			if(Context.getInstance().getUsuario().getNombreUsuario().equals("administrator")){
				setResponsePage(HomePageAdministrativo.class);
				setRedirect(true);
			}
			else{
				setResponsePage(HomePageCiudadano.class);
				setRedirect(true);
			}
		}else{
			setResponsePage(LoginPage.class);
			setRedirect(true);
		}
	}

	
}
