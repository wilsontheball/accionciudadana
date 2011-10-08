package ar.com.thinksoft.ac.intac.utils.classes;

/**
 * Clase que define que tipo de funciones va a responder servicio Rest.
 * 
 * @since 08-10-2011
 * @author Paul
 */
public abstract class FuncionRest {
	// Funciones tipo GET
	public static final String GETPERFIL = "perfil";
	public static final String GETRECLAMOS = "reclamos";

	// Funciones tipo POST
	public static final String POSTUSUARIO = "postusuario";
	public static final String POSTRECLAMO = "postreclamo";
}
