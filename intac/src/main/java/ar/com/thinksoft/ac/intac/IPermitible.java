package ar.com.thinksoft.ac.intac;

/**
 * Reprensenta la interfaz que deberen tener todas las paginas webs
 * @author adriel
 *
 */
public interface IPermitible {

	/**
	 * Este metodo tiene que ser implementado en las subclases y debe devolver
	 * el permiso que debe tener la pagina web implementeda.
	 * 
	 */
	public abstract IPermiso getPermisoNecesario();

	/**
	 * Determina si un usuario puede acceder a dicha pagina
	 */
	public boolean puedeAcceder(IUsuario usuario);

}
