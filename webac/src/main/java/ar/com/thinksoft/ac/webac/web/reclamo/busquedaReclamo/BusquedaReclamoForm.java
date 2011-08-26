package ar.com.thinksoft.ac.webac.web.reclamo.busquedaReclamo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.wicket.Request;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.inmethod.grid.DataProviderAdapter;
import com.inmethod.grid.column.PropertyColumn;
import com.inmethod.grid.datagrid.DataGrid;
import com.inmethod.grid.datagrid.DefaultDataGrid;

import ar.com.thinksoft.ac.intac.*;
import ar.com.thinksoft.ac.webac.reclamo.*;
import ar.com.thinksoft.ac.webac.web.gridExtension.ButtonColumn;
import ar.com.thinksoft.ac.webac.web.gridExtension.CustomDataGrid;

@SuppressWarnings("serial")
public class BusquedaReclamoForm extends Form<IReclamo> {
	
	private DataGrid grid;
	
	public BusquedaReclamoForm(String id) {
		super(id);
		CompoundPropertyModel<IReclamo> model = new CompoundPropertyModel<IReclamo>(new Reclamo());
		setModel(model);
		
		add(new TextField<String>("calleIncidente",this.createBind(model,"calleIncidente")));
		add(new TextField<String>("alturaIncidente",this.createBind(model,"alturaIncidente")));
		
		add(new TextField<String>("FechaReclamo",this.createBind(model,"FechaReclamo")));
		
		DropDownChoice<String> dropDownListTipo = new DropDownChoice<String>("tipoIncidente", this.createBind(model,"tipoIncidente"),EnumTipoReclamo.getListaTiposReclamo());
		dropDownListTipo.setNullValid(true);
		add(dropDownListTipo);
		
		DropDownChoice<String> dropDownListEstado = new DropDownChoice<String>("EstadoDescripcion", this.createBind(model,"EstadoDescripcion"),EnumEstadosReclamo.getlistaEstadosReclamo());
		dropDownListEstado.setNullValid(true);
		add(dropDownListEstado);
		
		DropDownChoice<String> dropDownListPrioridad = new DropDownChoice<String>("Prioridad", this.createBind(model,"Prioridad"),EnumPrioridadReclamo.getlistaPrioridadReclamo());
		dropDownListPrioridad.setNullValid(true);
		add(dropDownListPrioridad);
			
		add(new Button("busqueda"));
		
		armarGrilla(ReclamoManager.getInstance().obtenerTodosReclamos());
        add(grid);
        
        add(new Button("detalle"));
        add(new Button("cancelar"));
        add(new Button("unificar"));
        
	}

	private IModel<String> createBind(CompoundPropertyModel<IReclamo> model,String property){
		return model.bind(property);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	protected void onSubmit() {
		if("Buscar reclamo".equals(getRequest().getParameter("busqueda"))){
			IReclamo reclamo = getModelObject();
			ListDataProvider<IReclamo> listDataProvider = new ListDataProvider<IReclamo>(ReclamoManager.getInstance().obtenerReclamosFiltrados(reclamo));
			grid.setDefaultModelObject(new DataProviderAdapter(listDataProvider));
		}
				
		
		if("Detalle del reclamo".equals(getRequest().getParameter("detalle"))){
			Collection<IModel> selected = grid.getSelectedItems();
			Reclamo reclamo;
	        for (IModel model : selected) {
	           reclamo = (Reclamo) model.getObject();
	           System.out.println(reclamo.getId());
	        }
		}
			
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void armarGrilla(List<IReclamo> lista) {
		ListDataProvider<IReclamo> listDataProvider = new ListDataProvider<IReclamo>(lista);

		List cols = (List) Arrays.asList(
			new PropertyColumn("idCol",new Model<String>("Id"),"Id").setInitialSize(250)
																	.setResizable(false),
            new PropertyColumn("calleCol",new Model<String>("Calle del Incidente"), "calleIncidente").setInitialSize(250),
            new PropertyColumn("alturaCol",new Model<String>("Altura"), "alturaIncidente").setInitialSize(100),
            new PropertyColumn("fechaCol",new Model<String>("Fecha del reclamo"), "FechaReclamo").setInitialSize(150),
            new PropertyColumn("tipoCol",new Model<String>("Tipo de Incidente"), "tipoIncidente").setInitialSize(250),
            new PropertyColumn("estadoCol",new Model<String>("Estado del reclamo"), "EstadoDescripcion").setInitialSize(150),
            new PropertyColumn("prioridadCol",new Model<String>("Prioridad del reclamo"), "Prioridad").setInitialSize(150),
            new PropertyColumn("observacionesCol",new Model<String>("Observaciones"), "Observaciones").setInitialSize(398)
            );
        
		grid = new DefaultDataGrid("grid", new DataProviderAdapter(listDataProvider), cols);
		grid.setRowsPerPage(10);
        grid.setClickRowToSelect(true);
        grid.setAllowSelectMultiple(false);
        grid.setCleanSelectionOnPageChange(true);
	}
}
