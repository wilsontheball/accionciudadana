package ar.com.thinksoft.ac.andrac.dominio;

import ar.com.thinksoft.ac.intac.IImagen;

public class Imagen implements IImagen{
	
	public Imagen (byte[] bytes, String contentType, String fileName){
		
		this.setBytes(bytes);
		this.setContentType(contentType);
		this.setFileName(fileName);
	}

	public void setBytes(byte[] imageBytes) {
		// TODO Auto-generated method stub
		
	}

	public byte[] getBytes() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setContentType(String content) {
		// TODO Auto-generated method stub
		
	}

	public String getContentType() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setFileName(String name) {
		// TODO Auto-generated method stub
		
	}

	public String getFileName() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
