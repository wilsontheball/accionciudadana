package ar.com.thinksoft.ac.webac.reclamo;

import java.io.IOException;

import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.util.file.File;

public class ImageFactory {
	
	private static final String PATH = "src/main/webapp/tempImages/";
	private File archivo;
	private FileUpload archivoSubido;
	
	public ImageFactory(FileUpload file) throws Exception{
		archivoSubido = file;
		crearDirectorioTemporal();
		createImage();
		
	}

	private void createImage() throws Exception, IOException {
		
		//TODO: controlar que no tenga otra extension distinta de bmp,png,jpeg,gif...
		archivo = new File(PATH + getFileName());
		if(!archivo.exists())
			archivo.createNewFile();
		archivo.write(archivoSubido.getInputStream());
	}
	
	private void crearDirectorioTemporal(){
		File directory = new File(PATH);
		if(!directory.exists()){
			directory.mkdir();
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
	
	/*public Image getImage() throws Exception{
		Image imagen = null;
		if(archivo.exists()){
			ServletContext servletContext = WebApplication.get().getServletContext();
			String path = (String)servletContext.getRealPath("/tempImages/");
			imagen = new Image("imagePreview");
			imagen.add(new AttributeModifier("src", new Model<String>()));
		}
		else
			throw new Exception("Problema en el upload");
		
		return imagen;
	}*/
	
	public void deleteImage(){
		if(archivo.exists())
			archivo.delete();
	}
}
