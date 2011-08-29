package ar.com.thinksoft.ac.webac.web.reclamo.altaReclamo;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.resource.ContextRelativeResource;

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
		        try {
					File archivoImagen = file.writeToTempFile();
					_self.addOrReplace(new Image("imagePreview",new ContextRelativeResource(archivoImagen.getAbsolutePath())));
					
				} catch (IOException e) {
					e.printStackTrace();
				}
		    }

			@Override
			protected void onError(AjaxRequestTarget target){
			} 

		} );
		
		add(fileUploadField);
		add(new Image("imagePreview"));
		setMultiPart(true);
		add(new Button("guardarReclamo") {
				@Override
				public void onSubmit() {
					Reclamo reclamo = _self.getModelObject();
					reclamo.setId();
					Date fecha = new Date();
					SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
					reclamo.setFechaReclamo(formato.format(fecha));
					reclamo.activar();
					setResponsePage(HomePage.class);
		        }
			}
	    );
		
		
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
	}
	
}

/*
 * http://www.massapi.com/source/apache-wicket-1.4.9/src/testing/wicket-threadtest/src/main/java/org/apache/wicket/threadtest/apps/app1/ResourceTestPage.java.html
 * 
 		BufferedImage image = new BufferedImage(16, 16, BufferedImage.TYPE_INT_RGB);
                Graphics gfx = image.getGraphics();
                gfx.setColor(new Color(random.nextFloat(), random.nextFloat(), random.nextFloat()));
                gfx.fillRect(0, 0, 32, 32);
                gfx.dispose();
 
                // Write it into a byte array as a JPEG.
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(baos);
                try
                {
                    encoder.encode(image);
                }
                catch (IOException e)
                {
                    throw new WicketRuntimeException(e);
                }
                
  		
  		final byte[] imageData = baos.toByteArray();
 		item.add(new Image("image", new WebResource()
                {
                    private static final long serialVersionUID = 1L;
 
                    @Override
                    public IResourceStream getResourceStream()
                    {
                        return new IResourceStream()
                        {
                            private static final long serialVersionUID = 1L;
 
                            public Time lastModifiedTime()
                            {
                                return Time.now();
                            }
 
                            public void setLocale(Locale locale)
                            {
                            }
 
                            public long length()
                            {
                                return imageData.length;
                            }
 
                            public Locale getLocale()
                            {
                                return null;
                            }
 
                            // Make a 16x16 randomly background-coloured JPEG.
                            public InputStream getInputStream()
                                throws ResourceStreamNotFoundException
                            {
                                return new ByteArrayInputStream(imageData);
                            }
 
                            public String getContentType()
                            {
                                return "image/jpeg";
                            }
 
                            public void close() throws IOException
                            {
                            }
 
                        };
                    }
 
                }));
 
 
 
 */
