package ar.com.thinksoft.ac.webac.seguridad;

import org.apache.wicket.markup.html.WebPage;

import ar.com.thinksoft.ac.intac.IUsuario;

/**
 * 
 * Esta clase es la representacion de una pagina que tiene permisos de seguridad.
 * Todo el que extienda de esta pagina deberá tener un codigo de seguridad para poder ser accedido.
 * 
 * @author Wilson
 *
 *
 * TODO: falta que la clase herede de WebPage asi que valido para todas las paginas webs del sistema. 
 *
 */
public abstract class Permitible extends WebPage{
	
	/**
	 * Este metodo tiene que ser implementado en las subclases y debe devolver
	 * el permiso que debe tener la pagina web implementeda.
	 * @return
	 */
	public abstract Permiso getPermisoNecesario();
	
	
	
	/**
	 * Verifica si el usuario posee permisos suficientes para poder acceder a dicha pagina.
	 * @return
	 */
	public boolean isValido(IUsuario usuario){

		return usuario.tenesPermisosPara(this.getPermisoNecesario());
	
	}
	
	
}
