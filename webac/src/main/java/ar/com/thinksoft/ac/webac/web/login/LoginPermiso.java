package ar.com.thinksoft.ac.webac.web.login;

import ar.com.thinksoft.ac.intac.IPermiso;
import ar.com.thinksoft.ac.webac.seguridad.Permiso;

public class LoginPermiso extends Permiso implements IPermiso {

	@Override
	public String getCodigoSeguridad() {
		return "login";
	}

}
