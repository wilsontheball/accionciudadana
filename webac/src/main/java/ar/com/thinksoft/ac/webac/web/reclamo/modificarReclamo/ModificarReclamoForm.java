package ar.com.thinksoft.ac.webac.web.reclamo.modificarReclamo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.wicket.PageParameters;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.authorization.strategies.role.metadata.MetaDataRoleAuthorizationStrategy;
import org.apache.wicket.markup.html.basic.Label;
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
import ar.com.thinksoft.ac.intac.EnumEstadosReclamo;
import ar.com.thinksoft.ac.intac.EnumPrioridadReclamo;
import ar.com.thinksoft.ac.intac.EnumTipoReclamo;
import ar.com.thinksoft.ac.intac.IImagen;
import ar.com.thinksoft.ac.intac.IReclamo;
import ar.com.thinksoft.ac.webac.logging.LogFwk;
import ar.com.thinksoft.ac.webac.mail.MailManager;
import ar.com.thinksoft.ac.webac.predicates.PredicatePorUUID;
import ar.com.thinksoft.ac.webac.reclamo.ImageFactory;
import ar.com.thinksoft.ac.webac.reclamo.Imagen;
import ar.com.thinksoft.ac.webac.reclamo.Reclamo;
import ar.com.thinksoft.ac.webac.reclamo.ReclamoManager;
import ar.com.thinksoft.ac.webac.web.configuracion.Configuracion;
import ar.com.thinksoft.ac.webac.web.reclamo.detalleReclamo.DetalleReclamoPage;

@SuppressWarnings("serial")
public class ModificarReclamoForm  extends Form<Reclamo>{

	private IReclamo reclamoOriginal = new Reclamo();
	private ModificarReclamoForm _self = this;
	private ImageFactory img = null;
	
	public ModificarReclamoForm(String id, String idReclamo) throws Exception {
		super(id);
		setMultiPart(true);
		List<IReclamo> lista = ReclamoManager.getInstance().obtenerReclamosFiltradosConPredicates(new PredicatePorUUID().filtrar(idReclamo));
		
		if(lista.size()!= 1){
			throw new Exception("error en la base de datos, por favor, comuniquese con el equipo de soporte tecnico");
		}
		reclamoOriginal = lista.get(0);
		
		IReclamo reclamo = new Reclamo();
		reclamo.clone(reclamoOriginal);
		
		CompoundPropertyModel<Reclamo> model = new CompoundPropertyModel<Reclamo>(reclamo);
		setModel(model);
		
		TextField<String> calle = new TextField<String>("calleIncidente",createBind(model,"CalleIncidente"));
		add(calle);
		
		TextField<String> altura = new TextField<String>("alturaIncidente",createBind(model,"AlturaIncidente"));
		add(altura);
		
		TextField<String> ciudadanoTextBox = new TextField<String>("CiudadanoGeneradorReclamo",createBind(model,"CiudadanoGeneradorReclamo"));
		ciudadanoTextBox.setEnabled(false);
		add(ciudadanoTextBox);
		
		DropDownChoice<String> dropDownListTipo = new DropDownChoice<String>("tipoIncidente", createBind(model,"tipoIncidente"),EnumTipoReclamo.getListaTiposReclamo());
		dropDownListTipo.setNullValid(true);
		add(dropDownListTipo);
		
		DropDownChoice<String> dropDownListBarrios = new DropDownChoice<String>("barrioIncidente", createBind(model,"barrioIncidente"),EnumBarriosReclamo.getListaBarriosReclamo());
		dropDownListBarrios.setNullValid(true);
		add(dropDownListBarrios);
		
		Label prioridadLabel = new Label("prioridadLabel","Prioridad: ");
		MetaDataRoleAuthorizationStrategy.authorize(prioridadLabel, RENDER, "ADMIN");
		MetaDataRoleAuthorizationStrategy.authorize(prioridadLabel, RENDER,"OPERADOR");
		add(prioridadLabel);
		
		DropDownChoice<String> dropDownListPrioridad = new DropDownChoice<String>("Prioridad", createBind(model,"Prioridad"),EnumPrioridadReclamo.getlistaPrioridadReclamo());
		dropDownListPrioridad.setNullValid(true);
		MetaDataRoleAuthorizationStrategy.authorize(dropDownListPrioridad, RENDER, "ADMIN");
		MetaDataRoleAuthorizationStrategy.authorize(dropDownListPrioridad, RENDER,"OPERADOR");
		add(dropDownListPrioridad);
		
		Label estadoLabel = new Label("estadoLabel","Estado: ");
		MetaDataRoleAuthorizationStrategy.authorize(estadoLabel, RENDER, "ADMIN");
		MetaDataRoleAuthorizationStrategy.authorize(estadoLabel, RENDER,"OPERADOR");
		add(estadoLabel);
		
		DropDownChoice<String> dropDownListEstado = new DropDownChoice<String>("EstadoDescripcion", createBind(model,"EstadoDescripcion"),EnumEstadosReclamo.getlistaEstadosReclamoSinAsociado());
		dropDownListEstado.setNullValid(true);
		MetaDataRoleAuthorizationStrategy.authorize(dropDownListEstado, RENDER, "ADMIN");
		MetaDataRoleAuthorizationStrategy.authorize(dropDownListEstado, RENDER,"OPERADOR");
		add(dropDownListEstado);
		
		TextArea<String> observaciones = new TextArea<String>("observaciones",createBind(model,"observaciones"));
		add(observaciones);
		
		final FileUploadField fileUploadField = new FileUploadField("imagen",new Model<FileUpload>()); 
		fileUploadField.add( new AjaxFormSubmitBehavior(this,"onchange") { 
			@Override
		    protected void onSubmit(AjaxRequestTarget arg0) { 
				final FileUpload file = fileUploadField.getFileUpload();
				try {
					img = new ImageFactory(file);
				} catch (Exception e) {
					LogFwk.getInstance(ModificarReclamoForm.class).error("Problemas al crear la imagen. Detalle: " + e.getMessage());
				}
		    }
			@Override
			protected void onError(AjaxRequestTarget target){
			} 

		} );
		
		add(fileUploadField);
	
		try{
			IImagen imagen = reclamoOriginal.getImagen();
			img = new ImageFactory(imagen);
			add(new Label("rutaImagen",imagen.getFileName()));
		}catch(Exception e){
			add(new Label("rutaImagen",""));
		}
		
		add(new Button("actualizarReclamo") {
				@Override
				public void onSubmit() {
					Reclamo reclamoModificado = _self.getModelObject();
					IReclamo reclamoO = reclamoOriginal;
					if(!isReclamoNoValido(reclamoModificado)){
						if(img != null){
							reclamoModificado.setImagen(new Imagen(img.getFileBytes(),img.getContentType(),img.getFileName()));
							img.deleteImage();
						}
						//Seteo fecha de modificacion
						Date fecha = new Date();
						SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
						reclamoModificado.setFechaUltimaModificacionReclamo(formato.format(fecha));
						
						//Cambio la prioridad de acuerdo a la prioridad elegida
						String prioridad = reclamoModificado.getPrioridad();
						if(prioridad != "" && prioridad != null && !reclamoO.getPrioridad().equals(prioridad)){
							try {
								reclamoModificado.setPrioridad(prioridad);
								MailManager.getInstance().enviarMail(reclamoO.getMailCiudadanoGeneradorReclamo(), "Accion Ciudadana - Cambio de prioridad del reclamo", MailManager.getInstance().armarTextoCambioPrioridad(prioridad, reclamoO));
							} catch (Exception e) {
								LogFwk.getInstance(ModificarReclamoForm.class).error("Problema al enviar mail por cambio de prioridad. Detalle: " + e.getMessage());
							}
						}
						
						if (reclamoOriginal.getAlturaIncidente() != reclamoModificado.getAlturaIncidente() || 
							!reclamoOriginal.getCalleIncidente().equals(reclamoModificado.getCalleIncidente())){
							
							/*
							 * CONVERSION CALLE A COORDENADAS GPS
							 */
							GLatLng coordenadas = null;
							Double latitud,longitud;
							String direccion = reclamoModificado.getCalleIncidente() + " " + reclamoModificado.getAlturaIncidente() + ",Capital Federal, Argentina";
							Geocoder geocoder = new Geocoder(Configuracion.getInstance().getKeyGoogleMap());
							try {
								 coordenadas = geocoder.geocode(direccion);
								 latitud = coordenadas.getLat();
								 longitud = coordenadas.getLng();
								 reclamoModificado.setLatitudIncidente(latitud.toString());
								 reclamoModificado.setLongitudIncidente(longitud.toString());
							} catch (Exception e) {
								LogFwk.getInstance(ModificarReclamoForm.class).error("Problema al generar coordenadas. Stack: " + e);
							}
							// FIN CONVERSION CALLE A COORDENADAS GPS
							
						}

						//Cambio el estado de acuerdo al estado elegido
						String estado = reclamoModificado.getEstadoDescripcion();
						if(!estado.equals(reclamoO.getEstadoDescripcion())){
							if(estado != "" && estado != null){
								try {
									reclamoModificado.cambiarEstado(estado);
								} catch (Exception e) {
									LogFwk.getInstance(ModificarReclamoForm.class).error("Problema al enviar mail por cambio de estado. Detalle: " + e.getMessage());
								}
							}
						}else{
							ReclamoManager.getInstance().guardarReclamo(reclamoModificado);
						}
						//Elimino el reclamoOriginal, guardando el reclamoNuevo
						ReclamoManager.getInstance().eliminarReclamo(reclamoOriginal);
						
						PageParameters params = new PageParameters();
						params.add("reclamoId", reclamoModificado.getId());
						setResponsePage(DetalleReclamoPage.class, params);
						setRedirect(true);
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
					LogFwk.getInstance(ModificarReclamoForm.class).error("No existe archivo para borrar.");
				}
				PageParameters params = new PageParameters();
				params.add("reclamoId", reclamoOriginal.getId());
				setResponsePage(DetalleReclamoPage.class, params);
				setRedirect(true);
			}
		});
		
	}
	
	private IModel<String> createBind(CompoundPropertyModel<Reclamo> model,String property){
		return model.bind(property);
	}

	private boolean isReclamoNoValido(IReclamo reclamo){
		return reclamo.getAlturaIncidente() == null && reclamo.getCalleIncidente() == null && reclamo.getBarrioIncidente() == null
			&& reclamo.getPrioridad() == null && reclamo.getEstadoDescripcion() == null;
	}
	
	
}

