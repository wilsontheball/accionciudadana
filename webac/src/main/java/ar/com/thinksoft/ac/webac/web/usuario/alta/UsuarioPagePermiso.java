package ar.com.thinksoft.ac.webac.web.usuario.alta;

import ar.com.thinksoft.ac.intac.IPermiso;

public class UsuarioPagePermiso implements IPermiso {

	@Override
	public String getCodigoSeguridad() {
		return "usuario-page";
	}

	@Override
	public boolean equals(IPermiso permiso) {
		return false;
	}

}
