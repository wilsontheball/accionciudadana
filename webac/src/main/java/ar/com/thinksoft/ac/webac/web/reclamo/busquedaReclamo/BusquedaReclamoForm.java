package ar.com.thinksoft.ac.webac.web.reclamo.busquedaReclamo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.inmethod.grid.DataProviderAdapter;
import com.inmethod.grid.IGridColumn;
import com.inmethod.grid.column.PropertyColumn;
import com.inmethod.grid.datagrid.DataGrid;
import ar.com.thinksoft.ac.intac.*;
import ar.com.thinksoft.ac.webac.reclamo.*;
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
			
		armarGrilla(ReclamoManager.getInstance().obtenerTodosReclamos());
        add(grid);
	}

	private IModel<String> createBind(CompoundPropertyModel<IReclamo> model,String property){
		return model.bind(property);
	}
	
	@Override
	protected void onSubmit() {
		IReclamo reclamo = getModelObject();
		/*ListDataProvider<IReclamo> listDataProvider = new ListDataProvider<IReclamo>(ReclamoManager.getInstance().obtenerReclamosFiltrados(reclamo));
		grid.setDefaultModelObject(new DataProviderAdapter(listDataProvider));*/
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void armarGrilla(List<IReclamo> lista) {
		/*ListDataProvider<IReclamo> listDataProvider = new ListDataProvider<IReclamo>(lista);

		List<IGridColumn> cols = (List) Arrays.asList(
            new PropertyColumn("calleCol",new Model<String>("Calle del Incidente"), "calleIncidente"),
            new PropertyColumn("alturaCol",new Model<String>("Altura"), "alturaIncidente").setInitialSize(60),
            new PropertyColumn("fechaCol",new Model<String>("Fecha del reclamo"), "FechaReclamo"),
            new PropertyColumn("tipoCol",new Model<String>("Tipo de Incidente"), "tipoIncidente"),
            new PropertyColumn("estadoCol",new Model<String>("Estado del reclamo"), "EstadoDescripcion"),
            new PropertyColumn("prioridadCol",new Model<String>("Prioridad del reclamo"), "Prioridad"));
        
		grid = new CustomDataGrid("grid", new DataProviderAdapter(listDataProvider), cols);
        grid.setRowsPerPage(10);
        grid.setClickRowToSelect(true);
        grid.setAllowSelectMultiple(false);
        grid.setCleanSelectionOnPageChange(true);*/

	}
}
