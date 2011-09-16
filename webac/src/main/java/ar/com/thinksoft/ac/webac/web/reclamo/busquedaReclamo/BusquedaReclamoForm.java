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
import ar.com.thinksoft.ac.webac.reclamo.Reclamo;
import ar.com.thinksoft.ac.webac.reclamo.ReclamoManager;
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
	private static final String PATH = "src/main/webapp/export/";
	private Dialog dialogCancelar = null;
	private Dialog dialogCancelarError = null;
	private Dialog dialogDetalle = null;
	private Dialog dialogUnificar = null;
	private Dialog dialogUnificarError = null;
	private ListDataProvider<IReclamo> listDataProvider = new ListDataProvider<IReclamo>(ReclamoManager.getInstance().obtenerTodosReclamos());
	
	@SuppressWarnings("rawtypes")
	public BusquedaReclamoForm(String id) {
		
		super(id);
		
		CompoundPropertyModel<IReclamo> model = new CompoundPropertyModel<IReclamo>(new Reclamo());
		
		setModel(model);
		
		add(new TextField<String>("calleIncidente",this.createBind(model,"calleIncidente")));
		
		add(new TextField<String>("alturaIncidente",this.createBind(model,"alturaIncidente")));
		
		add(new TextField<String>("CiudadanoGeneradorReclamo",this.createBind(model,"CiudadanoGeneradorReclamo")));
		
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
					IReclamo reclamo = _self.getModelObject();
					listDataProvider = new ListDataProvider<IReclamo>(ReclamoManager.getInstance().obtenerReclamosFiltrados(reclamo));
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
		        reclamo.cancelarReclamo();
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
				if(selected.size()>=1){
					dialogCancelar.open(target);
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
	    
	    add(new AjaxLink("unificarReclamo"){
	    	@Override
	    	public void onClick(AjaxRequestTarget target){
	    		Collection<IModel> selected = grid.getSelectedItems();
	    		if(selected.size()==2){
	    			dialogUnificar.open(target);
		    	}else{
					dialogUnificarError.open(target);
				}
	    	}
	    });
	    
	    dialogUnificar.add(new AjaxLink("unificar"){
			@Override
			public void onClick(AjaxRequestTarget target){
				Collection<IModel> selected = grid.getSelectedItems();
				List<IReclamo> listaReclamosSeleccionados = obtenerReclamosSeleccionados(selected);
				IReclamo reclamo = listaReclamosSeleccionados.get(0);
				IReclamo reclamo2 = listaReclamosSeleccionados.get(1);
				reclamo.unificar(reclamo2);
				dialogUnificar.close(target);
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
        add(new Button("exportar"){
        	@Override
			public void onSubmit() {
        		
        		ByteArrayResource bar = null;
				try {
					bar = new ByteArrayResource("application/pdf", exportTable());

				} catch (Exception e) {
					e.printStackTrace();
				}
        		IResourceStream stream = bar.getResourceStream();
        		RequestCycle.get().setRequestTarget(new ResourceStreamRequestTarget(stream, "accionCiudadana.pdf"));
        		
        	}
        });
        
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
																	.setResizable(false),
																	
            new PropertyColumn("calleCol",new Model<String>("Calle del Incidente"), "calleIncidente").setInitialSize(26)
            																						 .setResizable(false)
            																						 .setWrapText(true)
            																						 .setSizeUnit(SizeUnit.EX),
            																						 
            new PropertyColumn("alturaCol",new Model<String>("Altura"), "alturaIncidente").setInitialSize(10)
            																			  .setResizable(false)
            																			  .setSizeUnit(SizeUnit.EX),
            																			  
            new PropertyColumn("barrioCol",new Model<String>("Barrio"), "barrioIncidente").setInitialSize(20)
            																			  .setResizable(false)
            																			  .setWrapText(true)
            																			  .setSizeUnit(SizeUnit.EX), 																			  
            
            new PropertyColumn("comunaCol",new Model<String>("Comuna"), "comunaIncidente").setInitialSize(15)
            																			  .setResizable(false)
            																			  .setWrapText(true)
            																			  .setSizeUnit(SizeUnit.EX),
            																				
            new PropertyColumn("fechaCol",new Model<String>("Fecha de alta"), "FechaReclamo").setInitialSize(15)
            																		 		.setResizable(false)
            																		 		.setSizeUnit(SizeUnit.EX),
            																						
            new PropertyColumn("tipoCol",new Model<String>("Tipo"), "tipoIncidente").setInitialSize(25)
            																		.setResizable(false)
            																		.setSizeUnit(SizeUnit.EX),
            																					 
            new PropertyColumn("estadoCol",new Model<String>("Estado"), "EstadoDescripcion").setInitialSize(20)
            																				.setResizable(false)
            																				.setSizeUnit(SizeUnit.EX),

            new PropertyColumn("prioridadCol",new Model<String>("Prioridad"), "Prioridad").setInitialSize(20)
            																				 .setResizable(false)
            																				 .setSizeUnit(SizeUnit.EX),
            																						  
            new PropertyColumn("ciudadanoCol",new Model<String>("Ciudadano"), "CiudadanoGeneradorReclamo").setInitialSize(20)
          																							.setResizable(false)
          																							.setSizeUnit(SizeUnit.EX), 
            																						  
            new PropertyColumn("observacionesCol",new Model<String>("Observaciones"), "Observaciones").setInitialSize(65)
            																						  .setResizable(false)
            																						  .setWrapText(true)
            																						  .setSizeUnit(SizeUnit.EX)
            );
        
		grid = new DefaultDataGrid("grid", new DataProviderAdapter(listDataProvider), cols);
		grid.setRowsPerPage(10);
        grid.setClickRowToSelect(true);
        grid.setAllowSelectMultiple(true);
        grid.setClickRowToDeselect(true);
        grid.setCleanSelectionOnPageChange(false);
        
        
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public byte[] exportTable() throws Exception{
		byte[] arrayBytes = null;
		
	  	//obtain a list of objects for the report
	    List<IReclamo> reclamos = ReclamoManager.getInstance().obtenerTodosReclamos();
	
	    // pass parameters to the report
	    Map parameters = new HashMap();
	    parameters.put("Title", "Listado de Reclamos - Accion Ciudadana");
	
	    try {
	      // compile report design
	      JasperReport jasperReport = JasperCompileManager.compileReport(PATH + "design.jrxml");
	      
	      // create an object datasourse from the pilots list
	      ObjectDataSource dataSource = new ObjectDataSource(reclamos);
	
	      // fill the report 
	      JasperPrint jasperPrint = JasperFillManager.fillReport(
	          jasperReport, parameters, dataSource);
	      
	      // export result to the *.pdf
	      //JasperExportManager.exportReportToPdfFile(jasperPrint,
	    	//  PATH + "accionCiudadana.pdf");
	      
	     arrayBytes = JasperExportManager.exportReportToPdf(jasperPrint);
	      
	      // or export to *.html
	      /*JasperExportManager.exportReportToHtmlFile(jasperPrint,
	    	  PATH + "the-pilot-report.html");
	*/
	    } catch (JRException e) {
	    	throw new Exception("Imposible exportar a pdf. Consulte con nuestro soporte tecnico");
	    }
		return arrayBytes;
	}
	
}
