package ar.com.thinksoft.ac.webac.web.reclamo.modificarReclamo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.wicket.PageParameters;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
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

import ar.com.thinksoft.ac.intac.EnumBarriosReclamo;
import ar.com.thinksoft.ac.intac.EnumEstadosReclamo;
import ar.com.thinksoft.ac.intac.EnumPrioridadReclamo;
import ar.com.thinksoft.ac.intac.EnumTipoReclamo;
import ar.com.thinksoft.ac.intac.IImagen;
import ar.com.thinksoft.ac.intac.IReclamo;
import ar.com.thinksoft.ac.webac.logging.LogFwk;
import ar.com.thinksoft.ac.webac.predicates.PredicatePorUUID;
import ar.com.thinksoft.ac.webac.reclamo.ImageFactory;
import ar.com.thinksoft.ac.webac.reclamo.Imagen;
import ar.com.thinksoft.ac.webac.reclamo.Reclamo;
import ar.com.thinksoft.ac.webac.reclamo.ReclamoManager;
import ar.com.thinksoft.ac.webac.web.reclamo.detalleReclamo.DetalleReclamoForm;
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
		
		if(lista.size()!= 1)
			throw new Exception("error en la base de datos, por favor, comuniquese con el equipo de soporte tecnico");
		
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
		
		DropDownChoice<String> dropDownListPrioridad = new DropDownChoice<String>("Prioridad", createBind(model,"Prioridad"),EnumPrioridadReclamo.getlistaPrioridadReclamo());
		dropDownListPrioridad.setNullValid(true);
		dropDownListPrioridad.setEnabled(this.isPermitido());
		add(dropDownListPrioridad);
		
		DropDownChoice<String> dropDownListEstado = new DropDownChoice<String>("EstadoDescripcion", createBind(model,"EstadoDescripcion"),EnumEstadosReclamo.getlistaEstadosReclamo());
		dropDownListEstado.setNullValid(true);
		dropDownListEstado.setEnabled(this.isPermitido());
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
			LogFwk.getInstance(DetalleReclamoForm.class).error("no existe imagen para este reclamo");
			add(new Label("rutaImagen",""));
		}
		
		add(new Button("actualizarReclamo") {
				@Override
				public void onSubmit() {
					Reclamo reclamoModificado = _self.getModelObject();
					if(!isReclamoNoValido(reclamoModificado)){
						reclamoModificado.setImagen(new Imagen(img.getFileBytes(),img.getContentType(),img.getFileName()));
						
						//Seteo fecha de modificacion
						Date fecha = new Date();
						SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
						reclamoModificado.setFechaUltimaModificacionReclamo(formato.format(fecha));
						
						//Cambio el estado de acuerdo al estado elegido
						String estado = reclamoModificado.getEstadoDescripcionDefault();
						reclamoModificado.cambiarEstado(estado);
						
						//Elimino el reclamoOriginal, guardando el reclamoNuevo
						ReclamoManager.getInstance().eliminarReclamo(reclamoOriginal);
						
						img.deleteImage();
						PageParameters params = new PageParameters();
						params.add("reclamoId", reclamoModificado.getId());
						setResponsePage(DetalleReclamoPage.class, params);
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
			}
		});
		
	}
	
	/*
	 * TODO: Implementar con los permisos de modificacion de estado
	 */
	private boolean isPermitido() {
		return true;
	}
	
	private IModel<String> createBind(CompoundPropertyModel<Reclamo> model,String property){
		return model.bind(property);
	}

	private boolean isReclamoNoValido(IReclamo reclamo){
		return reclamo.getAlturaIncidente() == null && reclamo.getCalleIncidente() == null && reclamo.getBarrioIncidente() == null
			&& reclamo.getPrioridad() == null && reclamo.getEstadoDescripcion() == null;
	}
	
	
}

