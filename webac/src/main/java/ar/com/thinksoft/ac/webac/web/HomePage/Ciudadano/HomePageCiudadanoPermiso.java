package ar.com.thinksoft.ac.webac.web.HomePage.Ciudadano;

import ar.com.thinksoft.ac.webac.seguridad.Permiso;

public class HomePageCiudadanoPermiso extends Permiso {

	@Override
	public String getCodigoSeguridad() {
		return "homePageCiudadano";
	}

}
