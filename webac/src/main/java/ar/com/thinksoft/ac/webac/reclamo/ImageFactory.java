package ar.com.thinksoft.ac.webac.reclamo;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.util.file.File;

import ar.com.thinksoft.ac.intac.IImagen;

public class ImageFactory {
	
	private static final String PATH = "src/main/webapp/tempImages/";
	private File image;
	private File directorio;
	
	private byte[] bytesImagen;
	private String fileNameImagen;
	private String contentTypeImagen;
	
	public ImageFactory(FileUpload file) throws Exception{
		bytesImagen = file.getBytes();
		fileNameImagen = file.getClientFileName();
		contentTypeImagen = file.getContentType();
		crearDirectorioTemporal();
		createImage(file.getInputStream(),fileNameImagen, contentTypeImagen);
	}
	
	public ImageFactory(IImagen imagen) throws Exception{
		bytesImagen = imagen.getBytes();
		fileNameImagen = imagen.getFileName();
		contentTypeImagen = imagen.getContentType();
		
		ByteArrayInputStream arrayStream = new ByteArrayInputStream(bytesImagen);
		crearDirectorioTemporal();
		createImage(arrayStream, fileNameImagen, contentTypeImagen);
	}

	private void createImage(InputStream stream, String fileName, String contentType) throws Exception{
		if("image/jpeg".equals(contentType) || "image/jpg".equals(contentType)||"image/png".equals(contentType)
		||"image/bmp".equals(contentType)||"image/gif".equals(contentType)){
			
			image = new File(PATH + fileName);
			if(!image.exists())
				image.createNewFile();
			image.write(stream);
		}else
			throw new Exception("no tiene un formato válido");
	}
	
	public void deleteImage(){
		if(image.exists())
			image.delete();
	}
	
	private void crearDirectorioTemporal(){
		directorio = new File(PATH);
		if(!directorio.exists()){
			directorio.mkdir();
		}
	}
	
	public byte[] getFileBytes(){
		return bytesImagen;
	}
	
	public String getContentType(){
		return contentTypeImagen;
	}
	
	public String getFileName(){
		return fileNameImagen;
	}
	
}
