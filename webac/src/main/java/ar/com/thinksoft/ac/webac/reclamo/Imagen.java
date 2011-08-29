package ar.com.thinksoft.ac.webac.reclamo;

import org.apache.wicket.markup.html.form.upload.FileUpload;

import ar.com.thinksoft.ac.intac.IImagen;

public class Imagen implements IImagen{

	private byte[] bytes;
	
	
	public byte[] getBytes() {
		return bytes;
	}
	
	public void setBytes(byte[] imageBytes){
		bytes = imageBytes;
	}
	
	public void setBytesFromFileUpload(FileUpload file){
		bytes = file.getBytes();
	}

}
