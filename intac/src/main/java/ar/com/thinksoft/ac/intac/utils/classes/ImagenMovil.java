package ar.com.thinksoft.ac.intac.utils.classes;

import ar.com.thinksoft.ac.intac.IImagen;

/**
 * Es un DTO para intercambiar los datos de imagen entre Andrac y Wilsond.
 * Cumpliendo con GSON la clase asegura que los objetos de ambos extremos tengan
 * los mismos nombres de atributos.
 * 
 * @since 07-10-2011
 * @author Paul
 */
public abstract class ImagenMovil implements IImagen {

	public static final String TIPO_JPG = "jpg";

	protected byte[] imagenBytes;
	protected String contentType;
}
