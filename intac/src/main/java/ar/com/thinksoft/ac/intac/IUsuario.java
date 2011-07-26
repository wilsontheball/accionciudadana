package ar.com.thinksoft.ac.intac;

public interface IUsuario {

	/**
	 * Verifica si ese codido de seguridad se encuentra en alguno de los
	 * permisos seteados.
	 * 
	 * @param codigoSeguridad
	 */
	boolean tenesPermisosPara(IPermitible permitible);

	void addPermiso(IPermiso permiso);

	/**
	 * devuelve la cantidad de permisos de un usuario, es solo a modo de test
	 * 
	 * @return
	 */
	int cantidadPermisos();

}
