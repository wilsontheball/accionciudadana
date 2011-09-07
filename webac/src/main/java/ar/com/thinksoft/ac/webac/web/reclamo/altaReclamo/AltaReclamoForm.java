package ar.com.thinksoft.ac.webac.web.reclamo.altaReclamo;

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
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import ar.com.thinksoft.ac.intac.EnumBarriosReclamo;
import ar.com.thinksoft.ac.intac.EnumTipoReclamo;
import ar.com.thinksoft.ac.webac.HomePage;
import ar.com.thinksoft.ac.webac.reclamo.ImageFactory;
import ar.com.thinksoft.ac.webac.reclamo.Imagen;
import ar.com.thinksoft.ac.webac.reclamo.Reclamo;
import ar.com.thinksoft.ac.webac.web.Context;

@SuppressWarnings("serial")
public class AltaReclamoForm extends Form<Reclamo> {
	
	private AltaReclamoForm _self = this;
	private ImageFactory img = null;
	
	public AltaReclamoForm(String id) {
		super(id);
		CompoundPropertyModel<Reclamo> model = new CompoundPropertyModel<Reclamo>(new Reclamo());
		setModel(model);
		
		TextField<String> calle = new TextField<String>("calleIncidente",this.createBind(model,"calleIncidente"));
		//calle.setRequired(true);
		add(calle);
		
		TextField<String> altura = new TextField<String>("alturaIncidente",this.createBind(model,"alturaIncidente"));
		//altura.setRequired(true);
		add(altura);
		
		TextField<String> ciudadanoTextBox = new TextField<String>("ciudadanoIncidente",this.getName());
		ciudadanoTextBox.setEnabled(false);
		add(ciudadanoTextBox);
		
		DropDownChoice<String> dropDownList = new DropDownChoice<String>("tipoIncidente", this.createBind(model,"tipoIncidente"),EnumTipoReclamo.getListaTiposReclamo());
		dropDownList.setNullValid(true);
		//dropDownList.setRequired(true);
		add(dropDownList);
		
		DropDownChoice<String> dropDownListBarrios = new DropDownChoice<String>("barrioIncidente", this.createBind(model,"barrioIncidente"),EnumBarriosReclamo.getListaBarriosReclamo());
		dropDownListBarrios.setNullValid(true);
		add(dropDownListBarrios);
		
		TextArea<String> observaciones = new TextArea<String>("observaciones",this.createBind(model, "observaciones"));
		add(observaciones);

		final FileUploadField fileUploadField = new FileUploadField("imagen",new Model<FileUpload>()); 
		fileUploadField.add( new AjaxFormSubmitBehavior(this,"onchange") { 
			@Override
		    protected void onSubmit(AjaxRequestTarget arg0) { 
				final FileUpload file = fileUploadField.getFileUpload();
				try {
					img = new ImageFactory(file);
				} catch (Exception e) {
					e.printStackTrace();
				}
		    }
			@Override
			protected void onError(AjaxRequestTarget target){
			} 

		} );
		add(fileUploadField);
		
		setMultiPart(true);
		add(new Button("guardarReclamo") {
				@Override
				public void onSubmit() {
					Reclamo reclamo = _self.getModelObject();
					reclamo.setId();
					reclamo.setImagen(new Imagen(img.getFileBytes(),img.getContentType(),img.getFileName()));
					Date fecha = new Date();
					SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
					reclamo.setFechaReclamo(formato.format(fecha));
					reclamo.activar();
					img.deleteImage();
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