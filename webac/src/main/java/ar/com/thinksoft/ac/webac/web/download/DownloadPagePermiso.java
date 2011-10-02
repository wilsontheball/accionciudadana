package ar.com.thinksoft.ac.webac.web.download;

import ar.com.thinksoft.ac.webac.seguridad.Permiso;

public class DownloadPagePermiso extends Permiso{

	@Override
	public String getCodigoSeguridad() {
		return "download";
	}

}
