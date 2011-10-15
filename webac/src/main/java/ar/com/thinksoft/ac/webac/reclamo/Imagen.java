package ar.com.thinksoft.ac.webac.reclamo;

import java.io.Serializable;

import org.apache.wicket.markup.html.form.upload.FileUpload;

import ar.com.thinksoft.ac.intac.IImagen;

@SuppressWarnings("serial")
public class Imagen implements IImagen,Serializable{

	private byte[] bytes;
	private String contentType;
	private String fileName;
	
	public Imagen(byte[] bytes, String content, String name){
		setBytes(bytes);
		setContentType(content);
		setFileName(name);
	}
	
	public byte[] getBytes() {
		return bytes;
	}
	
	public void setBytes(byte[] imageBytes){
		bytes = imageBytes;
	}
	
	public void setBytesFromFileUpload(FileUpload file){
		bytes = file.getBytes();
	}
	
	public void setContentType(String content){
		contentType = content;
	}
	
	public String getContentType(){
		return contentType;
	}
	
	public void setFileName(String name){
		fileName = name;
	}
	
	public String getFileName(){
		return fileName;
	}

}
