package ar.com.thinksoft.ac.webac;

import ar.com.thinksoft.ac.webac.seguridad.Permiso;

public class HomePagePermiso extends Permiso {

	@Override
	public String getCodigoSeguridad() {
		return "home-page";
	}

}
