package ar.com.thinksoft.ac.webac.web.reclamo.altaReclamo;

import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.image.NonCachingImage;
import org.apache.wicket.markup.html.image.resource.DynamicImageResource;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.resource.ByteArrayResource;

import ar.com.thinksoft.ac.intac.EnumTipoReclamo;
import ar.com.thinksoft.ac.webac.HomePage;
import ar.com.thinksoft.ac.webac.reclamo.Reclamo;
import ar.com.thinksoft.ac.webac.web.Context;

@SuppressWarnings("serial")
public class AltaReclamoForm extends Form<Reclamo> {
	
	private AltaReclamoForm _self = this;
	
	public AltaReclamoForm(String id) {
		super(id);
		CompoundPropertyModel<Reclamo> model = new CompoundPropertyModel<Reclamo>(new Reclamo());
		setModel(model);
		
		add(new TextField<String>("calleIncidente",this.createBind(model,"calleIncidente")));
		add(new TextField<String>("alturaIncidente",this.createBind(model,"alturaIncidente")));
		
		add(new TextField<String>("latitudIncidente",this.createBind(model,"latitudIncidente")));
		add(new TextField<String>("longitudIncidente",this.createBind(model,"longitudIncidente")));
		
		TextField<String> ciudadanoTextBox = new TextField<String>("ciudadanoIncidente",this.getName());
		ciudadanoTextBox.setEnabled(false);
		add(ciudadanoTextBox);
		
		DropDownChoice<String> dropDownList = new DropDownChoice<String>("tipoIncidente", this.createBind(model,"tipoIncidente"),EnumTipoReclamo.getListaTiposReclamo());
		dropDownList.setNullValid(true);
		add(dropDownList);
		add(new TextArea<String>("observaciones",this.createBind(model, "observaciones")));
		
		
		final FileUploadField fileUploadField = new FileUploadField("imagen",new Model<FileUpload>()); 
		fileUploadField.add( new AjaxFormSubmitBehavior(this,"onchange") { 
			
			@Override
		    protected void onSubmit(AjaxRequestTarget arg0) { 
		        final FileUpload file = fileUploadField.getFileUpload();
		        //ByteArrayResource resourceImage = new ByteArrayResource("image/jpeg", file.getBytes());
		        //Image image = new Image("imagePreview", resourceImage);
		        //_self.addOrReplace(image);
		        
		        _self.addOrReplace( new NonCachingImage("imagePreview", new AbstractReadOnlyModel() { 
			                            @Override 
			                            public Object getObject() { 
			                                return new ImageResource(file.getBytes(),"png"); 
			                            }
		                            }));
		    }

			@Override
			protected void onError(AjaxRequestTarget target){
			} 

		} );
		
		add(fileUploadField);
		add(new Image("imagePreview"));
		setMultiPart(true);
	}
	
	private IModel<String> createBind(CompoundPropertyModel<Reclamo> model,String property){
		return model.bind(property);
	}
	
	private IModel<String> getName(){
		return new IModel<String>(){

			@Override
			public void detach() {
			}

			@Override
			public String getObject() {
				return Context.getInstance().getUsuario().getNombreUsuario();
			}

			@Override
			public void setObject(String object) {
			}

		};
	}
	
	@Override
	protected void onSubmit() {

		Reclamo reclamo = getModelObject();
		reclamo.setId();
		Date fecha = new Date();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		reclamo.setFechaReclamo(formato.format(fecha));
		//reclamo.activar();
		//setResponsePage(HomePage.class);

	}
	
	public class ImageResource extends DynamicImageResource { 

	    // has to save this. or get the image another way! 
	    private byte[] image; 

	    public ImageResource(byte[] image, String format) { 
	        this.image = image; 
	        setFormat(format); 
	    } 

	    public ImageResource(BufferedImage image) { 
	        this.image = toImageData(image); 
	    } 

	    @Override 
	    protected byte[] getImageData() { 
	        if (image != null) { 
	            return image; 
	        } else { 
	            return new byte[0]; 
	        } 

	    } 

	    @Override 
	    protected int getCacheDuration() { 
	        
	        return 3600*24; 
	    } 

	} 
	
}
