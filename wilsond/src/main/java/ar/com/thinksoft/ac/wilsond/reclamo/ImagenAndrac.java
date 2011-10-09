package ar.com.thinksoft.ac.wilsond.reclamo;

import ar.com.thinksoft.ac.intac.utils.classes.ImagenMovil;

/**
 * Representa una imagen sacada con la camara.
 * 
 * @since 07-10-2011
 * @author Paul
 */
public class ImagenAndrac extends ImagenMovil {

	public ImagenAndrac(byte[] bytes, String contentType, String fileName) {

		this.setBytes(bytes);
		this.setContentType(contentType);
		this.setFileName(fileName);
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