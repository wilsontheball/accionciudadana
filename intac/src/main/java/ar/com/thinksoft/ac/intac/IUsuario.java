package ar.com.thinksoft.ac.intac;

public interface IUsuario {

	/**
	 * Verifica si ese codido de seguridad se encuentra en alguno de los permisos seteados.
	 * @param codigoSeguridad
	 */
	boolean tenesPermisosPara(IPermitible permitible);

	void addPermiso(IPermiso permiso);

	int cantidadPermisos();

}
