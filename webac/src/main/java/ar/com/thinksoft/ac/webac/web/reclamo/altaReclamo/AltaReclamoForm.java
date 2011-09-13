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
import ar.com.thinksoft.ac.intac.EnumPrioridadReclamo;
import ar.com.thinksoft.ac.intac.EnumTipoReclamo;
import ar.com.thinksoft.ac.intac.IReclamo;
import ar.com.thinksoft.ac.webac.HomePage;
import ar.com.thinksoft.ac.webac.logging.LogFwk;
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
		setMultiPart(false);
		
		CompoundPropertyModel<Reclamo> model = new CompoundPropertyModel<Reclamo>(new Reclamo());
		setModel(model);
		
		TextField<String> calle = new TextField<String>("calleIncidente",this.createBind(model,"calleIncidente"));
		add(calle);
		
		TextField<String> altura = new TextField<String>("alturaIncidente",this.createBind(model,"alturaIncidente"));
		add(altura);
		
		TextField<String> ciudadanoTextBox = new TextField<String>("CiudadanoGeneradorReclamo",this.getName());
		ciudadanoTextBox.setEnabled(false);
		add(ciudadanoTextBox);
		
		DropDownChoice<String> dropDownListTipo = new DropDownChoice<String>("tipoIncidente", this.createBind(model,"tipoIncidente"),EnumTipoReclamo.getListaTiposReclamo());
		dropDownListTipo.setNullValid(true);
		add(dropDownListTipo);
		
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
				}
		    }
			@Override
			protected void onError(AjaxRequestTarget target){
			} 

		} );
		
		add(fileUploadField);
		
		
		
		add(new Button("guardarReclamo") {
				@Override
				public void onSubmit() {
					Reclamo reclamo = _self.getModelObject();
					if(!isReclamoNoValido(reclamo)){
						//metodos agregados a mano
						reclamo.setId();
						reclamo.setPrioridad(EnumPrioridadReclamo.noAsignada.getPrioridad());
						reclamo.setImagen(new Imagen(img.getFileBytes(),img.getContentType(),img.getFileName()));
						reclamo.setComunaIncidente();
						reclamo.setCiudadanoGeneradorReclamo(Context.getInstance().getUsuario().getNombreUsuario());
						Date fecha = new Date();
						SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
						reclamo.setFechaReclamo(formato.format(fecha));
						reclamo.setFechaUltimaModificacionReclamo(formato.format(fecha));
						//fin metodos agregados a mano
						
						reclamo.activar();
						img.deleteImage();
						setResponsePage(HomePage.class);
					}
		        }
			}
	    );
		
		add(new Button("cancelar") {
			@Override
			public void onSubmit() {
				try{
					img.deleteImage();
				}catch(Exception e){
					LogFwk.getInstance(AltaReclamoForm.class).error("No existe archivo para borrar.");
				}
				setResponsePage(HomePage.class);
			}
		});
		
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
	
	private boolean isReclamoNoValido(IReclamo reclamo){
		return reclamo.getAlturaIncidente() == null && reclamo.getCalleIncidente() == null && reclamo.getBarrioIncidente() == null;
	}

}