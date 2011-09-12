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
import ar.com.thinksoft.ac.webac.HomePage;
import ar.com.thinksoft.ac.webac.logging.LogFwk;
import ar.com.thinksoft.ac.webac.predicates.PredicatePorUUID;
import ar.com.thinksoft.ac.webac.reclamo.ImageFactory;
import ar.com.thinksoft.ac.webac.reclamo.Imagen;
import ar.com.thinksoft.ac.webac.reclamo.Reclamo;
import ar.com.thinksoft.ac.webac.reclamo.ReclamoManager;
import ar.com.thinksoft.ac.webac.repository.Repository;
import ar.com.thinksoft.ac.webac.web.Context;
import ar.com.thinksoft.ac.webac.web.reclamo.altaReclamo.AltaReclamoForm;
import ar.com.thinksoft.ac.webac.web.reclamo.detalleReclamo.DetalleReclamoForm;
import ar.com.thinksoft.ac.webac.web.reclamo.detalleReclamo.DetalleReclamoPage;

@SuppressWarnings("serial")
public class ModificarReclamoForm  extends Form<Reclamo>{

	private IReclamo reclamoModificado = new Reclamo();
	private ModificarReclamoForm _self = this;
	private ImageFactory img = null;
	
	public ModificarReclamoForm(String id, String idReclamo) throws Exception {
		super(id);
		setMultiPart(true);
		List<IReclamo> lista = ReclamoManager.getInstance().obtenerReclamosFiltradosConPredicates(new PredicatePorUUID().filtrar(idReclamo));
		
		if(lista.size()!= 1)
			throw new Exception("error en la base de datos, por favor, comuniquese con el equipo de soporte tecnico");
		
		reclamoModificado = lista.get(0);
		
		CompoundPropertyModel<Reclamo> model = new CompoundPropertyModel<Reclamo>(reclamoModificado);
		setModel(model);
		
		TextField<String> calle = new TextField<String>("calleIncidente",new Model<String>(reclamoModificado.getCalleIncidente()));
		add(calle);
		
		TextField<String> altura = new TextField<String>("alturaIncidente",new Model<String>(reclamoModificado.getAlturaIncidente()));
		add(altura);
		
		TextField<String> ciudadanoTextBox = new TextField<String>("CiudadanoGeneradorReclamo",new Model<String>(reclamoModificado.getCiudadanoGeneradorReclamo()));
		ciudadanoTextBox.setEnabled(false);
		add(ciudadanoTextBox);
		
		DropDownChoice<String> dropDownListTipo = new DropDownChoice<String>("tipoIncidente", new Model<String>(reclamoModificado.getTipoIncidente()),EnumTipoReclamo.getListaTiposReclamo());
		dropDownListTipo.setNullValid(true);
		add(dropDownListTipo);
		
		DropDownChoice<String> dropDownListBarrios = new DropDownChoice<String>("barrioIncidente", new Model<String>(reclamoModificado.getBarrioIncidente()),EnumBarriosReclamo.getListaBarriosReclamo());
		dropDownListBarrios.setNullValid(true);
		add(dropDownListBarrios);
		
		DropDownChoice<String> dropDownListPrioridad = new DropDownChoice<String>("Prioridad", new Model<String>(reclamoModificado.getPrioridad()),EnumPrioridadReclamo.getlistaPrioridadReclamo());
		dropDownListPrioridad.setNullValid(true);
		add(dropDownListPrioridad);
		
		DropDownChoice<String> dropDownListEstado = new DropDownChoice<String>("EstadoDescripcion", new Model<String>(reclamoModificado.getEstadoDescripcion()),EnumEstadosReclamo.getlistaEstadosReclamo());
		dropDownListEstado.setNullValid(true);
		add(dropDownListEstado);
		
		TextArea<String> observaciones = new TextArea<String>("observaciones",new Model<String>(reclamoModificado.getObservaciones()));
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
			IImagen imagen = reclamoModificado.getImagen();
			img = new ImageFactory(imagen);
			add(new Label("rutaImagen",imagen.getFileName()));
		}catch(Exception e){
			LogFwk.getInstance(DetalleReclamoForm.class).error("no existe imagen para este reclamo");
			add(new Label("rutaImagen",""));
		}
		
		add(new Button("actualizarReclamo") {
				@Override
				public void onSubmit() {
					IReclamo nuevoReclamo = _self.getModelObject();
					if(!isReclamoNoValido(nuevoReclamo)){
						//metodos agregados a mano
						nuevoReclamo.setImagen(new Imagen(img.getFileBytes(),img.getContentType(),img.getFileName()));
						nuevoReclamo.setComunaIncidente();
						
						
						/*Que sea fecha de modificacion
						 * Date fecha = new Date();
						SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
						reclamo.setFechaReclamo(formato.format(fecha));*/
						//fin metodos agregados a mano
						
						//reclamo.activar(); fijarse q descripcion tiene y guardar con el nuevo estado.
						
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
				params.add("reclamoId", reclamoModificado.getId());
				setResponsePage(DetalleReclamoPage.class, params);
			}
		});
		
	}
	
	private IModel<String> createBind(CompoundPropertyModel<Reclamo> model,String property){
		return model.bind(property);
	}
	
	private boolean isReclamoNoValido(IReclamo reclamo){
		return reclamo.getAlturaIncidente() == null && reclamo.getCalleIncidente() == null && reclamo.getBarrioIncidente() == null;
	}
	
	
}

