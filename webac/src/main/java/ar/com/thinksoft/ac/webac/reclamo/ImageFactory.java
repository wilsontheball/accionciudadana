package ar.com.thinksoft.ac.webac.reclamo;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.util.file.File;

import ar.com.thinksoft.ac.intac.IImagen;
import ar.com.thinksoft.ac.webac.exceptions.FormatoImagenException;
import ar.com.thinksoft.ac.webac.logging.LogFwk;
import ar.com.thinksoft.ac.webac.web.configuracion.Configuracion;

public class ImageFactory {
	
	private File image;
	private File directorio;
	
	private byte[] bytesImagen;
	private String fileNameImagen;
	private String contentTypeImagen;
	
	public ImageFactory(FileUpload file) throws Exception{
		Configuracion.getInstance().cargarConfiguracion();
		bytesImagen = file.getBytes();
		fileNameImagen = file.getClientFileName();
		contentTypeImagen = file.getContentType();
		crearDirectorioTemporal();
		createImage(file.getInputStream(),fileNameImagen, contentTypeImagen);
	}
	
	public ImageFactory(IImagen imagen) throws Exception{
		Configuracion.getInstance().cargarConfiguracion();
		bytesImagen = imagen.getBytes();
		fileNameImagen = imagen.getFileName();
		contentTypeImagen = imagen.getContentType();
		
		ByteArrayInputStream arrayStream = new ByteArrayInputStream(bytesImagen);
		crearDirectorioTemporal();
		createImage(arrayStream, fileNameImagen, contentTypeImagen);
	}

	private void createImage(InputStream stream, String fileName, String contentType) throws Exception{
		if("image/jpeg".equals(contentType) || "image/jpg".equals(contentType)||"image/png".equals(contentType)
		||"image/bmp".equals(contentType)||"image/gif".equals(contentType) || "jpg".equals(contentType)){
			
			image = new File(Configuracion.getInstance().getPathTempImages() + fileName);
			if(!image.exists())
				image.createNewFile();
			image.write(stream);
		}else{
			LogFwk.getInstance(ImageFactory.class).error("La imagen no tiene un formato valido");
			throw new FormatoImagenException("La imagen no tiene un formato valido.");
		}
	}
	
	public void deleteImage(){
		if(image.exists())
			image.delete();
	}
	
	private void crearDirectorioTemporal(){
		directorio = new File(Configuracion.getInstance().getPathTempImages());
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
