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

import wicket.contrib.gmap.api.GLatLng;
import wicket.contrib.gmap.util.Geocoder;
import ar.com.thinksoft.ac.intac.EnumBarriosReclamo;
import ar.com.thinksoft.ac.intac.EnumPrioridadReclamo;
import ar.com.thinksoft.ac.intac.EnumTipoReclamo;
import ar.com.thinksoft.ac.intac.IReclamo;
import ar.com.thinksoft.ac.webac.AccionCiudadanaSession;
import ar.com.thinksoft.ac.webac.logging.LogFwk;
import ar.com.thinksoft.ac.webac.reclamo.ImageFactory;
import ar.com.thinksoft.ac.webac.reclamo.Imagen;
import ar.com.thinksoft.ac.webac.reclamo.Reclamo;
import ar.com.thinksoft.ac.webac.web.configuracion.Configuracion;
import ar.com.thinksoft.ac.webac.web.reclamo.busquedaReclamo.BusquedaReclamoPage;

@SuppressWarnings("serial")
public class AltaReclamoForm extends Form<Reclamo> {
	
	private AltaReclamoForm _self = this;
	private ImageFactory img = null;
	
	public AltaReclamoForm(String id) {
		super(id);
		setMultiPart(false);
		
		CompoundPropertyModel<IReclamo> model = new CompoundPropertyModel<IReclamo>(new Reclamo());
		setDefaultModel(model);
		
		TextField<String> calle = new TextField<String>("calleIncidente",this.createBind(model,"calleIncidente"));
		add(calle);
		
		TextField<String> altura = new TextField<String>("alturaIncidente",this.createBind(model,"alturaIncidente"));
		add(altura);
		
		TextField<String> latitudIncidente = new TextField<String>("latitudIncidente",this.createBind(model,"latitudIncidente"));
		add(latitudIncidente);
		
		TextField<String> longitudIncidente = new TextField<String>("longitudIncidente",this.createBind(model,"longitudIncidente"));
		add(longitudIncidente);
		
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
						LogFwk.getInstance(AltaReclamoForm.class).error("Problemas al crear la imagen. Detalle: " + e.getMessage());
					}
		    }
			@Override
			protected void onError(AjaxRequestTarget target){
			} 

		} );
		
		add(fileUploadField);
		
		
		
		add(new Button("guardarReclamo") {
				@SuppressWarnings("unchecked")
				@Override
				public void onSubmit() {
					IModel<IReclamo> model = (IModel<IReclamo>) _self.getDefaultModel();
					IReclamo reclamo = model.getObject();
					if(!isReclamoNoValido(reclamo)){
						//metodos agregados a mano
						reclamo.setId();
						
						try {
							reclamo.setPrioridad(EnumPrioridadReclamo.noAsignada.getPrioridad());
						} catch (Exception e1) {
							LogFwk.getInstance(AltaReclamoForm.class).error("No se pudo enviar mail al cambiar prioridad. Detalle: " + e1.getMessage());
						}
						
						if(img != null){
							reclamo.setImagen(new Imagen(img.getFileBytes(),img.getContentType(),reclamo.getId()));
							img.deleteImage();
						}
						reclamo.setCiudadanoGeneradorReclamo(((AccionCiudadanaSession)getSession()).getUsuario().getNombreUsuario());
						reclamo.setMailCiudadanoGeneradorReclamo(((AccionCiudadanaSession)getSession()).getUsuario().getMail());
						Date fecha = new Date();
						SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
						reclamo.setFechaReclamo(formato.format(fecha));
						reclamo.setFechaUltimaModificacionReclamo(formato.format(fecha));
						//fin metodos agregados a mano
						
						/*
						 * CONVERSION CALLE A COORDENADAS GPS
						 */
						GLatLng coordenadas = null;
						Double latitud,longitud;
						String direccion = reclamo.getCalleIncidente() + " " + reclamo.getAlturaIncidente() + ",Capital Federal, Argentina";
						Geocoder geocoder = new Geocoder(Configuracion.getInstance().getKeyGoogleMap());
						try {
							 coordenadas = geocoder.geocode(direccion);
							 latitud = coordenadas.getLat();
							 longitud = coordenadas.getLng();
							 reclamo.setLatitudIncidente(latitud.toString());
							 reclamo.setLongitudIncidente(longitud.toString());
						} catch (Exception e) {
							LogFwk.getInstance(AltaReclamoPage.class).error("Problema al generar coordenadas. Detalle: " + e);
						}
						
						// FIN CONVERSION CALLE A COORDENADAS GPS
						try{
							reclamo.activar();
						} catch (Exception e1) {
							LogFwk.getInstance(AltaReclamoForm.class).error("No se pudo enviar mail al crear el reclamo. Detalle: " + e1.getMessage());
						}
						
						setResponsePage(BusquedaReclamoPage.class);
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
				setResponsePage(BusquedaReclamoPage.class);
			}
		});
		
	}
	
	private IModel<String> createBind(CompoundPropertyModel<IReclamo> model,String property){
		return model.bind(property);
	}
	
	private IModel<String> getName(){
		return new IModel<String>(){

			@Override
			public void detach() {
			}

			@Override
			public String getObject() {
				return ((AccionCiudadanaSession)getSession()).getUsuario().getNombreUsuario();
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