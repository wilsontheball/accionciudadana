package ar.com.thinksoft.ac.webac.web.reclamo.busquedaReclamo;

import ar.com.thinksoft.ac.intac.IPermiso;
import ar.com.thinksoft.ac.webac.seguridad.Permiso;

public class BusquedaReclamoPermiso extends Permiso implements IPermiso {

	@Override
	public String getCodigoSeguridad() {
		return "busquedaReclamo";
	}

}
