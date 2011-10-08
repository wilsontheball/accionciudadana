package ar.com.thinksoft.ac.andrac.dominio;

import ar.com.thinksoft.ac.intac.utils.classes.ImagenMovil;

/**
 * Representa una imagen sacada con la camara.
 * 
 * @since 07-10-2011
 * @author Paul
 */
public class Imagen extends ImagenMovil {

	public Imagen(byte[] bytes, String contentType) {

		this.setBytes(bytes);
		this.setContentType(contentType);
	}

	public void setBytes(byte[] imagenBytes) {
		this.imagenBytes = imagenBytes;
	}

	public byte[] getBytes() {
		return this.imagenBytes;
	}

	public void setContentType(String content) {
		this.contentType = content;
	}

	public String getContentType() {
		return this.contentType;
	}

	public void setFileName(String name) {
		// No hace nada.
	}

	public String getFileName() {
		// No hace nada.
		return "";
	}
}
