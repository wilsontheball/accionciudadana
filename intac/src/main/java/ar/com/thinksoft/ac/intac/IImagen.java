package ar.com.thinksoft.ac.intac;

public interface IImagen {
	
	public void setBytes(byte[] imageBytes);
	public byte[] getBytes();
	
	public void setContentType(String content);
	public String getContentType();
	
	public void setFileName(String name);
	public String getFileName();
}
