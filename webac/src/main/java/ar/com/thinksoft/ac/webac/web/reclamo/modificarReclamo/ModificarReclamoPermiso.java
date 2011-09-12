package ar.com.thinksoft.ac.webac.web.reclamo.modificarReclamo;

import ar.com.thinksoft.ac.intac.IPermiso;
import ar.com.thinksoft.ac.webac.seguridad.Permiso;

public class ModificarReclamoPermiso  extends Permiso implements IPermiso {

	@Override
	public String getCodigoSeguridad() {
		return "modificarReclamo";
	}

}
