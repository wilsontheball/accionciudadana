package ar.com.thinksoft.ac.intac.utils.collections;

public class ElementNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1491062033897295020L;
	
	public ElementNotFoundException() {
		super("El elemento especificado no se encuentra en la coleccion");
	}

}
