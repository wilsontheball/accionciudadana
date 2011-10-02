package ar.com.thinksoft.ac.webac.web.usuario.alta;

import ar.com.thinksoft.ac.intac.IPermiso;
import ar.com.thinksoft.ac.webac.web.base.BasePage;

public class UsuarioNuevoPage extends BasePage{

	@Override
	public IPermiso getPermisoNecesario() {
		return null;
	}
	
	public UsuarioNuevoPage() {

	this.add(new UsuarioNuevoForm("form"));
	
	}
	
	

}
