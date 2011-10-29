package ar.com.thinksoft.ac.webac.web.reclamo.busquedaReclamo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.apache.wicket.PageParameters;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.authorization.strategies.role.Roles;
import org.apache.wicket.authorization.strategies.role.metadata.MetaDataRoleAuthorizationStrategy;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.target.resource.ResourceStreamRequestTarget;
import org.apache.wicket.resource.ByteArrayResource;
import org.apache.wicket.util.resource.IResourceStream;

import ar.com.thinksoft.ac.intac.EnumBarriosReclamo;
import ar.com.thinksoft.ac.intac.EnumEstadosReclamo;
import ar.com.thinksoft.ac.intac.EnumPrioridadReclamo;
import ar.com.thinksoft.ac.intac.EnumTipoReclamo;
import ar.com.thinksoft.ac.intac.IReclamo;
import ar.com.thinksoft.ac.webac.AccionCiudadanaSession;
import ar.com.thinksoft.ac.webac.logging.LogFwk;
import ar.com.thinksoft.ac.webac.predicates.PredicatePorCiudadano;
import ar.com.thinksoft.ac.webac.reclamo.Reclamo;
import ar.com.thinksoft.ac.webac.reclamo.ReclamoManager;
import ar.com.thinksoft.ac.webac.usuario.Usuario;
import ar.com.thinksoft.ac.webac.web.configuracion.Configuracion;
import ar.com.thinksoft.ac.webac.web.export.ObjectDataSource;
import ar.com.thinksoft.ac.webac.web.reclamo.detalleReclamo.DetalleReclamoPage;

import com.inmethod.grid.DataProviderAdapter;
import com.inmethod.grid.SizeUnit;
import com.inmethod.grid.column.PropertyColumn;
import com.inmethod.grid.datagrid.DataGrid;
import com.inmethod.grid.datagrid.DefaultDataGrid;
import com.visural.wicket.component.dialog.Dialog;

@SuppressWarnings("serial")
public class BusquedaReclamoForm extends Form<IReclamo> {
	
	private DataGrid grid;
	private BusquedaReclamoForm _self = this;
	private Dialog dialogCancelar = null;
	private Dialog dialogCancelarError = null;
	private Dialog dialogDetalle = null;
	private Dialog dialogUnificar = null;
	private Dialog dialogUnificarError = null;
	private ListDataProvider<IReclamo> listDataProvider;
	private Usuario ciudadano = ((AccionCiudadanaSession)getSession()).getUsuario();
	
	@SuppressWarnings("rawtypes")
	public BusquedaReclamoForm(String id) {
		
		super(id);
		AccionCiudadanaSession session = (AccionCiudadanaSession) getSession();
		if (session.getRoles().hasAnyRole(this.createRolesNeededForAdmin()))
			listDataProvider = new ListDataProvider<IReclamo>(ReclamoManager.getInstance().obtenerTodosReclamos());
		else if (session.getRoles().hasAnyRole(this.createRolesNeededForUser())) 
			listDataProvider = new ListDataProvider<IReclamo>(ReclamoManager.getInstance().obtenerReclamosFiltradosConPredicates(new PredicatePorCiudadano().filtrar(ciudadano.getNombreUsuario())));
		
		CompoundPropertyModel<IReclamo> model = new CompoundPropertyModel<IReclamo>(new Reclamo());
		
		setModel(model);
		
		add(new TextField<String>("calleIncidente",this.createBind(model,"calleIncidente")));
		
		add(new TextField<String>("alturaIncidente",this.createBind(model,"alturaIncidente")));
		
		Label usuario = new Label("CiudadanoGeneradorReclamoLabel","Usuario");
		MetaDataRoleAuthorizationStrategy.authorize(usuario, RENDER, "ADMIN");
		MetaDataRoleAuthorizationStrategy.authorize(usuario, RENDER,"OPERADOR");
		add(usuario);

		TextField<String> ciudadanoGenerador = new TextField<String>("CiudadanoGeneradorReclamo",this.createBind(model,"CiudadanoGeneradorReclamo"));
		MetaDataRoleAuthorizationStrategy.authorize(ciudadanoGenerador, RENDER, "ADMIN");
		MetaDataRoleAuthorizationStrategy.authorize(ciudadanoGenerador, RENDER, "OPERADOR");
		add(ciudadanoGenerador);
		
		add(new TextField<String>("FechaReclamo",this.createBind(model,"FechaReclamo")));
		
		add(new TextField<String>("FechaUltimaModificacionReclamo",this.createBind(model,"FechaUltimaModificacionReclamo")));
		
		DropDownChoice<String> dropDownListTipo = new DropDownChoice<String>("tipoIncidente", this.createBind(model,"tipoIncidente"),EnumTipoReclamo.getListaTiposReclamo());
		dropDownListTipo.setNullValid(true);
		add(dropDownListTipo);
		
		DropDownChoice<String> dropDownListEstado = new DropDownChoice<String>("EstadoDescripcion", this.createBind(model,"EstadoDescripcion"),EnumEstadosReclamo.getlistaEstadosReclamo());
		dropDownListEstado.setNullValid(true);
		add(dropDownListEstado);
		
		DropDownChoice<String> dropDownListBarrios = new DropDownChoice<String>("barrioIncidente", this.createBind(model,"barrioIncidente"),EnumBarriosReclamo.getListaBarriosReclamo());
		dropDownListBarrios.setNullValid(true);
		add(dropDownListBarrios);
		
		DropDownChoice<String> dropDownListComunas = new DropDownChoice<String>("comunaIncidente", this.createBind(model,"comunaIncidente"),EnumBarriosReclamo.getListaComunasReclamo());
		dropDownListComunas.setNullValid(true);
		add(dropDownListComunas);
		
		DropDownChoice<String> dropDownListPrioridad = new DropDownChoice<String>("Prioridad", this.createBind(model,"Prioridad"),EnumPrioridadReclamo.getlistaPrioridadReclamo());
		dropDownListPrioridad.setNullValid(true);
		add(dropDownListPrioridad);
			
		///////////// BUSCAR RECLAMO ////////////////////////
		add(new Button("busqueda"){
				@Override
				public void onSubmit() {
					IReclamo reclamoFiltro = _self.getModelObject();

					AccionCiudadanaSession session = (AccionCiudadanaSession) getSession();
					
					if (!session.getRoles().hasAnyRole(_self.createRolesNeededForAdmin())) 
						reclamoFiltro.setCiudadanoGeneradorReclamo(ciudadano.getNombreUsuario());
					
					List<IReclamo> listaReclamosFiltrados = ReclamoManager.getInstance().obtenerReclamosFiltrados(reclamoFiltro);
					
					listDataProvider = new ListDataProvider<IReclamo>(listaReclamosFiltrados);
					grid.setDefaultModelObject(new DataProviderAdapter(listDataProvider));
				}
			});
		armarGrilla();
        add(grid);
        
        ///////////// DETALLE RECLAMO ////////////////////////
        dialogDetalle = new Dialog("dialogDetalle");
        add(dialogDetalle);
        add(new AjaxLink("detalle"){
				@Override
				public void onClick(AjaxRequestTarget target){
					Collection<IModel> selected = grid.getSelectedItems();
					if(selected.size()==1){
						Reclamo reclamo = new Reclamo();
				        for (IModel model : selected) {
				           reclamo = (Reclamo) model.getObject();
				        }
				        PageParameters params =new PageParameters();
				        params.add("reclamoId", reclamo.getId());
				        params.add("esPadre", Boolean.FALSE.toString());
			            
				        setResponsePage(DetalleReclamoPage.class, params);
				        setRedirect(true);
					}else{
						dialogDetalle.open(target);
					}
				}
			});
        dialogDetalle.add(new AjaxLink("volver"){
	    	@Override
	    	public void onClick(AjaxRequestTarget target){
	    		dialogDetalle.close(target);
	    	}
	    });
        
        ///////////// CANCELAR RECLAMO ////////////////////////
        dialogCancelar = new Dialog("dialogCancelar");
	    add(dialogCancelar);
	    dialogCancelarError = new Dialog("dialogCancelarError");
	    add(dialogCancelarError);
	    
	    dialogCancelar.add(new AjaxLink("cancelarReclamo"){
			@Override
			public void onClick(AjaxRequestTarget target){
				Collection<IModel> selected = grid.getSelectedItems();
				Reclamo reclamo = new Reclamo();
		        for (IModel model : selected) {
		           reclamo = (Reclamo) model.getObject();
		        }
		        
		        try{
		        	reclamo.cancelarReclamo();
				} catch (Exception e1) {
					LogFwk.getInstance(BusquedaReclamoForm.class).error("No se pudo enviar mail al cancelar reclamo. Detalle: " + e1.getMessage());
				}
				
				dialogCancelar.close(target);
				setResponsePage(BusquedaReclamoPage.class);
		        setRedirect(true);
			}
	    });
	    dialogCancelar.add(new AjaxLink("volver"){
	    	@Override
	    	public void onClick(AjaxRequestTarget target){
	    		dialogCancelar.close(target);
	    	}
	    });
	    dialogCancelarError.add(new AjaxLink("volver"){
	    	@Override
	    	public void onClick(AjaxRequestTarget target){
	    		dialogCancelarError.close(target);
	    	}
	    });
	    
	    add(new AjaxLink("cancelar") {
            @Override
            public void onClick(AjaxRequestTarget target) {
            	Collection<IModel> selected = grid.getSelectedItems();
				if(selected.size()==1){
					Reclamo reclamo = new Reclamo();
			        for (IModel model : selected) {
			           reclamo = (Reclamo) model.getObject();
			        }
			        if(reclamo.isNotDown())
			        	dialogCancelar.open(target);
			        else
			        	dialogCancelarError.open(target);
				}else{
					dialogCancelarError.open(target);
				}
            }
        });
	    
	    ///////////// UNIFICAR RECLAMOS ////////////////////////
	    dialogUnificarError = new Dialog("dialogUnificarError");
	    add(dialogUnificarError);
	    dialogUnificar = new Dialog("dialogUnificar");
	    add(dialogUnificar);
	    
	    AjaxLink unificacion = new AjaxLink("unificarReclamo"){
	    	@Override
	    	public void onClick(AjaxRequestTarget target){
	    		Collection<IModel> selected = grid.getSelectedItems();
	    		if(selected.size()==2){
	    			List<IReclamo> lista = obtenerReclamosSeleccionados(selected);
	    			if(lista.get(0).isNotDown() && lista.get(1).isNotDown())
	    				dialogUnificar.open(target);
	    			else
	    				dialogUnificarError.open(target);
		    	}else{
					dialogUnificarError.open(target);
				}
	    	}
	    };
	    
	    MetaDataRoleAuthorizationStrategy.authorize(unificacion, RENDER, "ADMIN");
		MetaDataRoleAuthorizationStrategy.authorize(unificacion, RENDER,"OPERADOR");
	    add(unificacion);
	    
	    dialogUnificar.add(new AjaxLink("unificar"){
			@Override
			public void onClick(AjaxRequestTarget target){
				Collection<IModel> selected = grid.getSelectedItems();
				List<IReclamo> listaReclamosSeleccionados = obtenerReclamosSeleccionados(selected);
				IReclamo reclamo = listaReclamosSeleccionados.get(0);
				IReclamo reclamo2 = listaReclamosSeleccionados.get(1);
				
				if((reclamo.getFechaReclamo().compareTo(reclamo.getFechaReclamo()) <= 0))
					reclamo.unificar(reclamo2);
				else
					reclamo2.unificar(reclamo);
				
				
				dialogUnificar.close(target);
				setResponsePage(BusquedaReclamoPage.class);
		        setRedirect(true);
			}
			
			private List<IReclamo> obtenerReclamosSeleccionados(
					Collection<IModel> selected) {
				List<IReclamo> listaReclamosSeleccionados = new ArrayList<IReclamo>();
				Reclamo reclamo = new Reclamo();
				for(IModel modelReclamo: selected){
					reclamo = (Reclamo) modelReclamo.getObject();
					listaReclamosSeleccionados.add(reclamo);
				}
				return listaReclamosSeleccionados;
			}
	    });
	    
	    dialogUnificar.add(new AjaxLink("volver"){
	    	@Override
	    	public void onClick(AjaxRequestTarget target){
	    		dialogUnificar.close(target);
	    	}
	    });
	    
        dialogUnificarError.add(new AjaxLink("volver"){
	    	@Override
	    	public void onClick(AjaxRequestTarget target){
	    		dialogUnificarError.close(target);
	    	}
	    });
        
        
        ///////////// EXPORTAR ////////////////////////
        
        Button exportacion = new Button("exportar"){
        	@Override
			public void onSubmit() {
        		IReclamo reclamo = _self.getModelObject();
        		AccionCiudadanaSession session = (AccionCiudadanaSession) getSession();
        		
        		if (!session.getRoles().hasAnyRole(_self.createRolesNeededForAdmin())) 
        			reclamo.setCiudadanoGeneradorReclamo(ciudadano.getNombreUsuario());
        		
        		ByteArrayResource bar = null;
				try {
					bar = new ByteArrayResource("application/pdf", exportTable(reclamo));
				} catch (Exception e) {
					LogFwk.getInstance(BusquedaReclamoForm.class).error("Error en la exportacion. Detalle: " + e.getMessage());
				}
        		IResourceStream stream = bar.getResourceStream();
        		RequestCycle.get().setRequestTarget(new ResourceStreamRequestTarget(stream, "accionCiudadana.pdf"));
        		
        	}

        };
        MetaDataRoleAuthorizationStrategy.authorize(exportacion, RENDER, "ADMIN");
		MetaDataRoleAuthorizationStrategy.authorize(exportacion, RENDER,"OPERADOR");
        add(exportacion);
        
        ///////////// FIN ////////////////////////
	}

	private IModel<String> createBind(CompoundPropertyModel<IReclamo> model,String property){
		return model.bind(property);
	}
	
	@Override
	protected void onSubmit() {
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void armarGrilla() {
		
		List cols = (List) Arrays.asList(
			new PropertyColumn("idCol",new Model<String>("Id"),"Id").setInitialSize(0)
																	.setResizable(false)
																	.setSizeUnit(SizeUnit.PX),
																	
            new PropertyColumn("calleCol",new Model<String>("Calle del Incidente"), "calleIncidente").setInitialSize(150)
            																						 .setResizable(true)
            																						 .setWrapText(true)
            																						 .setSizeUnit(SizeUnit.PX),
            																						 
            new PropertyColumn("alturaCol",new Model<String>("Altura"), "alturaIncidente").setInitialSize(60)
            																			  .setWrapText(true)
            																			  .setReorderable(true)
            																			  .setResizable(true)
            																			  .setSizeUnit(SizeUnit.PX),
            																			  
            new PropertyColumn("barrioCol",new Model<String>("Barrio"), "barrioIncidente").setInitialSize(130)
            																			  .setWrapText(true)
            																			  .setReorderable(true)
            																			  .setResizable(true)
            																			  .setSizeUnit(SizeUnit.PX), 																			  
            
            new PropertyColumn("comunaCol",new Model<String>("Comuna"), "comunaIncidente").setInitialSize(100)
            																			  .setWrapText(true)
            																			  .setReorderable(true)
            																			  .setResizable(true)
            																			  .setSizeUnit(SizeUnit.PX),
            																				
            new PropertyColumn("fechaCol",new Model<String>("Fecha de alta"), "FechaReclamo").setInitialSize(100)
            																				.setReorderable(true)
            																				.setResizable(true)
            																		 		.setSizeUnit(SizeUnit.PX),
            																						
            new PropertyColumn("tipoCol",new Model<String>("Tipo"), "tipoIncidente").setInitialSize(160)
            																		.setReorderable(true)
            																		.setResizable(true)
            																		.setSizeUnit(SizeUnit.PX),
            																					 
            new PropertyColumn("estadoCol",new Model<String>("Estado"), "EstadoDescripcion").setInitialSize(100)
            																				.setReorderable(true)
            																				.setResizable(true)
            																				.setSizeUnit(SizeUnit.PX),

            new PropertyColumn("prioridadCol",new Model<String>("Prioridad"), "Prioridad").setInitialSize(100)
            																				 .setReorderable(true)
            																				 .setResizable(true)
            																				 .setSizeUnit(SizeUnit.PX),
            																						  
            new PropertyColumn("ciudadanoCol",new Model<String>("Ciudadano"), "CiudadanoGeneradorReclamo").setInitialSize(150)
          																							.setWrapText(true)
          																							.setReorderable(true)
            																						.setResizable(true)
          																							.setSizeUnit(SizeUnit.PX), 
            																						  
            new PropertyColumn("observacionesCol",new Model<String>("Observaciones"), "Observaciones").setInitialSize(350)
            																						  .setWrapText(true)
            																						  .setReorderable(true)
            																						  .setResizable(true)
            																						  .setSizeUnit(SizeUnit.PX)
            );
		
		grid = new DefaultDataGrid("grid", new DataProviderAdapter(listDataProvider), cols);
		grid.setRowsPerPage(10);
        grid.setClickRowToSelect(true);
        grid.setAllowSelectMultiple(true);
        grid.setClickRowToDeselect(true);
        grid.setCleanSelectionOnPageChange(false);
        
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public byte[] exportTable(IReclamo reclamo) throws Exception{
		byte[] arrayBytes = null;
		List<IReclamo> reclamos = new ArrayList<IReclamo>();
		if(conFiltro(reclamo)){
			reclamos = ReclamoManager.getInstance().obtenerReclamosFiltrados(reclamo);
		} else{
	  	//obtain a list of objects for the report
	     reclamos = ReclamoManager.getInstance().obtenerTodosReclamos();
		}
		
	    // pass parameters to the report
	    Map parameters = new HashMap();
	    parameters.put("Title", "Listado de Reclamos - Accion Ciudadana");
	
	    try {
	    	// compile report design
	    	Configuracion.getInstance().cargarConfiguracion();
		    JasperReport jasperReport = JasperCompileManager.compileReport(Configuracion.getInstance().getPathExportDesign());
		      
		    // create an object datasourse from the reclamo's list
		    ObjectDataSource dataSource = new ObjectDataSource(reclamos);
		
		    // fill the report 
		    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		      
		    arrayBytes = JasperExportManager.exportReportToPdf(jasperPrint);
	      
	    } catch (JRException e) {
	    	LogFwk.getInstance(BusquedaReclamoForm.class).error("Imposible exportar a pdf. Detalle: "+e.getMessage());
	    	throw new Exception("Imposible exportar a pdf. Consulte con nuestro soporte tecnico");
	    }
		return arrayBytes;
	}
	
	private boolean conFiltro(IReclamo reclamo) {
		return 	reclamo.getCalleIncidente() != null || reclamo.getAlturaIncidente() != null || reclamo.getBarrioIncidente() != null ||
				reclamo.getComunaIncidente() != null || reclamo.getCiudadanoGeneradorReclamo() != null || reclamo.getEstadoDescripcion() != null ||
				reclamo.getTipoIncidente() != null || reclamo.getPrioridad() != null || reclamo.getFechaReclamo() != null || 
				reclamo.getFechaUltimaModificacionReclamo() != null;
	}

	@SuppressWarnings("rawtypes")
	private List<IReclamo> obtenerReclamosSeleccionados(
			Collection<IModel> selected) {
		List<IReclamo> listaReclamosSeleccionados = new ArrayList<IReclamo>();
		Reclamo reclamo = new Reclamo();
		for(IModel modelReclamo: selected){
			reclamo = (Reclamo) modelReclamo.getObject();
			listaReclamosSeleccionados.add(reclamo);
		}
		return listaReclamosSeleccionados;
	}
	
	public Roles createRolesNeededForAdmin() {
		Roles roles = new Roles();
		roles.add("ADMIN");
		roles.add("OPERADOR");
		return roles;
	}

	public Roles createRolesNeededForUser() {
		Roles roles = new Roles();
		roles.add("CIUDADANO");
		return roles;
	}
	
	
	
}
