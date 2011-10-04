package ar.com.thinksoft.ac.webac.seguridad;

import org.apache.wicket.markup.html.WebPage;

import ar.com.thinksoft.ac.intac.IPermiso;
import ar.com.thinksoft.ac.intac.IPermitible;
import ar.com.thinksoft.ac.intac.IUsuario;


/**
 * 
 * Esta clase es la representacion de una pagina que tiene permisos de seguridad.
 * Todo el que extienda de esta pagina deberá tener un codigo de seguridad para poder ser accedido.
 * 
 * @author Wilson
 *
 *
 */
public abstract class Permitible extends WebPage implements IPermitible{
	
	
	public abstract IPermiso getPermisoNecesario();
	
	/**
	 * Determina si un usuario puede acceder a dicha pagina
	 */
	public boolean puedeAcceder(IUsuario usuario){
		return usuario.tenesPermisosPara(this);
	}
	
}
