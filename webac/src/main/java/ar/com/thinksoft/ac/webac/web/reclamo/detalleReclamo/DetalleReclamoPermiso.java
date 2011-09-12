package ar.com.thinksoft.ac.webac.web.reclamo.detalleReclamo;

import ar.com.thinksoft.ac.intac.IPermiso;
import ar.com.thinksoft.ac.webac.seguridad.Permiso;

public class DetalleReclamoPermiso  extends Permiso implements IPermiso {

	@Override
	public String getCodigoSeguridad() {
		return "detalleReclamo";
	}
}