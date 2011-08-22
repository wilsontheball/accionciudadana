package ar.com.thinksoft.ac.webac.web.reclamo.altaReclamo;

import ar.com.thinksoft.ac.intac.IPermiso;
import ar.com.thinksoft.ac.webac.seguridad.Permiso;

public class AltaReclamoPermiso extends Permiso implements IPermiso {

		@Override
		public String getCodigoSeguridad() {
			return "altaReclamo";
		}
}
