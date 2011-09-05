package ar.com.thinksoft.ac.webac.reclamo;

import java.io.IOException;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.image.ContextImage;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.resource.ContextRelativeResource;
import org.apache.wicket.util.file.File;

public class ImageFactory {
	
	private File archivo;
	
	public ImageFactory(FileUpload file) throws Exception{
		crearDirectorioTemporal();
		createImage(file);
		
	}

	private void createImage(FileUpload file) throws Exception, IOException {
		String nombreArchivo = file.getClientFileName();
		//TODO: controlar que no tenga otra extension distinta de bmp,png,jpeg,gif...
		
		archivo = new File("src/main/webapp/tempImages/" + nombreArchivo);
		if(!archivo.exists())
			archivo.createNewFile();
		archivo.write(file.getInputStream());
	}
	
	private void crearDirectorioTemporal(){
		File directory = new File("src/main/webapp/tempImages");
		if(!directory.exists()){
			directory.mkdir();
		}
	}
	
	public Image getImage() throws Exception{
		Image imagen = null;
		if(archivo.exists()){
			imagen = new Image("imagePreview", new ContextRelativeResource("src/main/webapp/tempImages/"+archivo.getName()));
		}
		else
			throw new Exception("Problema en el upload");
		
		return imagen;
	}
}
