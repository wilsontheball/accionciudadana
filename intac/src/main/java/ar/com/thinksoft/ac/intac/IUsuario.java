package ar.com.thinksoft.ac.intac;

public interface IUsuario {

	/**
	 * Verifica si ese codido de seguridad se encuentra en alguno de los permisos seteados.
	 * @param codigoSeguridad
	 */
	boolean tenesPermisosPara(IPermiso permiso);

	void addPermiso(IPermiso permiso);

	int cantidadPermisos();

}
