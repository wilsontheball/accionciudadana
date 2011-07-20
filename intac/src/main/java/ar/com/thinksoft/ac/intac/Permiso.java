package ar.com.thinksoft.ac.intac;

/**
 * 
 * @author Wilson
 *
 * Cada pagina debera tener asociado una implementacion de este permiso.
 * Cada usuario para poder acceder a dicha pagina debera poseer la implementacion de dicho permiso
 * en su coleccion de permisos. 
 *
 */
public interface Permiso {
	
	/**
	 * Determina la igualdad de permisos.
	 * @param permiso
	 * @return
	 */
	public boolean equals(Permiso permiso);
	
	/**
	 * Devuelve el codigo de seguridad que representa al permiso para poder comparar la igualdad.
	 * @return
	 */
	public String getCodigoSeguridad();

}
