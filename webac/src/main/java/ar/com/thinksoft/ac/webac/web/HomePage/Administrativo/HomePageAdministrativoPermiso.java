package ar.com.thinksoft.ac.webac.web.HomePage.Administrativo;

import ar.com.thinksoft.ac.webac.seguridad.Permiso;

public class HomePageAdministrativoPermiso extends Permiso {

	@Override
	public String getCodigoSeguridad() {
		return "homePageCiudadano";
	}

}
