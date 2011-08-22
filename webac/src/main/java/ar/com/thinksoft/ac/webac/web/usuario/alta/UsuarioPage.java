package ar.com.thinksoft.ac.webac.web.usuario.alta;

import ar.com.thinksoft.ac.intac.IPermiso;
import ar.com.thinksoft.ac.webac.web.base.BasePage;

public class UsuarioPage extends BasePage{

	@Override
	public IPermiso getPermisoNecesario() {
		return new UsuarioPagePermiso();
	}
	
	public UsuarioPage() {
		
		
		
		
		
	}

}
