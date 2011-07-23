package ar.com.thinksoft.ac.webac.seguridad;

import ar.com.thinksoft.ac.intac.IPermiso;

public abstract class Permiso implements IPermiso{

	/**
	 * Determina si es el mismo permiso.
	 */
	@Override
	public boolean equals(IPermiso permiso) {
		return permiso.getCodigoSeguridad().equals(this.getCodigoSeguridad());
	}

}
