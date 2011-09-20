package ar.com.thinksoft.ac.webac.web.configuracion;

import ar.com.thinksoft.ac.intac.IPermiso;
import ar.com.thinksoft.ac.webac.seguridad.Permiso;

public class ConfiguracionPermiso  extends Permiso implements IPermiso {

	@Override
	public String getCodigoSeguridad() {
		return "configuracion";
	}
}
