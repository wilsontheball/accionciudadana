package ar.com.thinksoft.ac.intac;

public interface Usuario {

	/**
	 * Verifica si ese codido de seguridad se encuentra en alguno de los permisos seteados.
	 * @param codigoSeguridad
	 * @return
	 */
	boolean tenesPermisosPara(String codigoSeguridad);

}
