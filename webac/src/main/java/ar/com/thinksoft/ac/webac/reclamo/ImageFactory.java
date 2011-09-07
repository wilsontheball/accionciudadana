package ar.com.thinksoft.ac.webac.reclamo;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.util.file.File;

public class ImageFactory {
	
	private static final String PATH = "src/main/webapp/tempImages/";
	private File image;
	private File directorio;
	private FileUpload archivoSubido;
	
	public ImageFactory(FileUpload file) throws Exception{
		archivoSubido = file;
		crearDirectorioTemporal();
		createImage(archivoSubido.getInputStream(),getFileName(), archivoSubido.getContentType());
	}
	
	public ImageFactory(Imagen imagen) throws Exception{
		ByteArrayInputStream arrayStream = new ByteArrayInputStream(imagen.getBytes());
		crearDirectorioTemporal();
		createImage(arrayStream,imagen.getFileName(), imagen.getContentType());
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
		return archivoSubido.getBytes();
	}
	
	public String getContentType(){
		return archivoSubido.getContentType();
	}
	
	public String getFileName(){
		return archivoSubido.getClientFileName();
	}
	
}
