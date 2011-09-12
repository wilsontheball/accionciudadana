package ar.com.thinksoft.ac.webac.web.reclamo.busquedaReclamo;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import ar.com.thinksoft.ac.intac.EnumBarriosReclamo;
import ar.com.thinksoft.ac.intac.EnumEstadosReclamo;
import ar.com.thinksoft.ac.intac.EnumPrioridadReclamo;
import ar.com.thinksoft.ac.intac.EnumTipoReclamo;
import ar.com.thinksoft.ac.intac.IReclamo;
import ar.com.thinksoft.ac.webac.reclamo.Reclamo;
import ar.com.thinksoft.ac.webac.reclamo.ReclamoManager;
import ar.com.thinksoft.ac.webac.web.reclamo.detalleReclamo.DetalleReclamoPage;

import com.inmethod.grid.DataProviderAdapter;
import com.inmethod.grid.SizeUnit;
import com.inmethod.grid.column.PropertyColumn;
import com.inmethod.grid.datagrid.DataGrid;
import com.inmethod.grid.datagrid.DefaultDataGrid;

@SuppressWarnings("serial")
public class BusquedaReclamoForm extends Form<IReclamo> {
	
	private DataGrid grid;
	private BusquedaReclamoForm _self = this;
	
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
			
		add(new Button("busqueda"){
				@Override
				public void onSubmit() {
					IReclamo reclamo = _self.getModelObject();
					ListDataProvider<IReclamo> listDataProvider = new ListDataProvider<IReclamo>(ReclamoManager.getInstance().obtenerReclamosFiltrados(reclamo));
					grid.setDefaultModelObject(new DataProviderAdapter(listDataProvider));
				}
			}
	    );
		
		armarGrilla(ReclamoManager.getInstance().obtenerTodosReclamos());
        add(grid);
        
        add(new Button("detalle"){
				@SuppressWarnings("rawtypes")
				@Override
				public void onSubmit() {
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
					}
				}
			}
        );
        
        add(new Button("cancelar"){
				@SuppressWarnings("rawtypes")
				@Override
				public void onSubmit() {
					Collection<IModel> selected = grid.getSelectedItems();
					if(selected.size()==1){
						Reclamo reclamo = new Reclamo();
				        for (IModel model : selected) {
				           reclamo = (Reclamo) model.getObject();
				        }
				        
				        /*
				         * DIALOG
				         * PREGUNTAR SI REALMENTE SE QUIERE HACER ESTO
				         */
				        
				        reclamo.cancelarReclamo();
				        setResponsePage(BusquedaReclamoPage.class);
				        setRedirect(true);
					}
				}
			}
        );
        
        add(new Button("unificar"){
				@SuppressWarnings("rawtypes")
				@Override
				public void onSubmit() {
					Collection<IModel> selected = grid.getSelectedItems();
					if(selected.size()!=1){
						/*
						 * UNIFICACION MANUAL
						 */
					}
				}
			}
        );
        
	}

	private IModel<String> createBind(CompoundPropertyModel<IReclamo> model,String property){
		return model.bind(property);
	}
	
	@Override
	protected void onSubmit() {
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void armarGrilla(List<IReclamo> lista) {
		ListDataProvider<IReclamo> listDataProvider = new ListDataProvider<IReclamo>(lista);

		List cols = (List) Arrays.asList(
			new PropertyColumn("idCol",new Model<String>("Id"),"Id").setInitialSize(0)
																	.setResizable(false),
																	
            new PropertyColumn("calleCol",new Model<String>("Calle del Incidente"), "calleIncidente").setInitialSize(25)
            																						 .setResizable(false)
            																						 .setWrapText(true)
            																						 .setSizeUnit(SizeUnit.EX),
            																						 
            new PropertyColumn("alturaCol",new Model<String>("Altura"), "alturaIncidente").setInitialSize(10)
            																			  .setResizable(false)
            																			  .setSizeUnit(SizeUnit.EX),
            																			  
            /*new PropertyColumn("barrioCol",new Model<String>("Barrio"), "barrioIncidente").setInitialSize(20)
            																			  .setResizable(false)
            																			  .setWrapText(true)
            																			  .setSizeUnit(SizeUnit.EX), 	*/																		  
            
            new PropertyColumn("comunaCol",new Model<String>("Comuna"), "comunaIncidente").setInitialSize(20)
            																			  .setResizable(false)
            																			  .setWrapText(true)
            																			  .setSizeUnit(SizeUnit.EX),
            																				
            new PropertyColumn("fechaCol",new Model<String>("Fecha del reclamo"), "FechaReclamo").setInitialSize(20)
            																					 .setResizable(false)
            																					 .setSizeUnit(SizeUnit.EX),
            																						
            new PropertyColumn("tipoCol",new Model<String>("Tipo de Incidente"), "tipoIncidente").setInitialSize(28)
            																					 .setResizable(false)
            																					 .setSizeUnit(SizeUnit.EX),
            																					 
            new PropertyColumn("estadoCol",new Model<String>("Estado del reclamo"), "EstadoDescripcion").setInitialSize(20)
            																							.setResizable(false)
            																							.setSizeUnit(SizeUnit.EX),

            new PropertyColumn("prioridadCol",new Model<String>("Prioridad del reclamo"), "Prioridad").setInitialSize(20)
            																						  .setResizable(false)
            																						  .setSizeUnit(SizeUnit.EX),
            																						  
           /* new PropertyColumn("ciudadanoCol",new Model<String>("Ciudadano"), "CiudadanoGeneradorReclamo").setInitialSize(20)
          																							.setResizable(false)
          																							.setSizeUnit(SizeUnit.EX), */
            																						  
            new PropertyColumn("observacionesCol",new Model<String>("Observaciones"), "Observaciones").setInitialSize(81)
            																						  .setResizable(false)
            																						  .setWrapText(true)
            																						  .setSizeUnit(SizeUnit.EX)
            );
        
		grid = new DefaultDataGrid("grid", new DataProviderAdapter(listDataProvider), cols);
		grid.setRowsPerPage(10);
        grid.setClickRowToSelect(true);
        grid.setAllowSelectMultiple(false);
        grid.setCleanSelectionOnPageChange(true);
        
	}
}
