package ar.com.thinksoft.ac.webac.seguridad;

import ar.com.thinksoft.ac.intac.Permiso;

public abstract class PermisoSimple implements Permiso{

	/**
	 * Determina si es el mismo permiso.
	 */
	@Override
	public boolean equals(Permiso permiso) {
		return permiso.getCodigoSeguridad().equals(this.getCodigoSeguridad());
	}

}
