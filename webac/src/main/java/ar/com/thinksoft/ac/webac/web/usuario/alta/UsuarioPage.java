package ar.com.thinksoft.ac.webac.web.usuario.alta;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.Model;

import ar.com.thinksoft.ac.intac.IPermiso;
import ar.com.thinksoft.ac.intac.IUsuario;
import ar.com.thinksoft.ac.webac.predicates.registro.PredicateTodosLosUsuarios;
import ar.com.thinksoft.ac.webac.repository.Repository;
import ar.com.thinksoft.ac.webac.web.base.BasePage;
import ar.com.thinksoft.ac.webac.web.reclamo.busquedaReclamo.BusquedaReclamoPage;

import com.inmethod.grid.DataProviderAdapter;
import com.inmethod.grid.IGridColumn;
import com.inmethod.grid.column.PropertyColumn;
import com.inmethod.grid.datagrid.DataGrid;

public class UsuarioPage extends BasePage {

	@Override
	public IPermiso getPermisoNecesario() {
		return new UsuarioPagePermiso();
	}

	public UsuarioPage() {
		
		add(CSSPackageResource.getHeaderContribution(BusquedaReclamoPage.class,"../../css/UsuarioPage.css"));
		
		this.add(this.createTablaUsuarios("grid"));

	}

	private Component createTablaUsuarios(String gridName) {

		ListDataProvider<IUsuario> dataProvider = new ListDataProvider<IUsuario>(
				this.getTodosLosUsuarios());
		List<IGridColumn> columnas = this.crearColumnas();

		DataGrid grid = new DataGrid(gridName, new DataProviderAdapter(
				dataProvider), columnas);

		grid.setRowsPerPage(10);
		grid.setClickRowToSelect(true);
		grid.setClickRowToDeselect(true);
		grid.setAllowSelectMultiple(false);
		grid.setCleanSelectionOnPageChange(true);

		return grid;
	}

	private List<IUsuario> getTodosLosUsuarios() {
		return Repository.getInstance().query(new PredicateTodosLosUsuarios());
	}

	private List<IGridColumn> crearColumnas() {
		List<IGridColumn> columnas = new ArrayList<IGridColumn>();

		columnas.add(new PropertyColumn("apellido", new Model<String>(
				"Apellido"), "apellido"));
		columnas.add(new PropertyColumn("nombre", new Model<String>("Nombre"),
				"nombre"));
		columnas.add(new PropertyColumn("nombreUsuario", new Model<String>(
				"Nombre de Usuario"), "nombreUsuario"));
		columnas.add(new PropertyColumn("dni", new Model<String>("DNI"), "dni"));
		columnas.add(new PropertyColumn("mail", new Model<String>("E-Mail"),
				"mail"));
		columnas.add(new PropertyColumn("telefono", new Model<String>(
				"Telefono"), "telefono"));

		return columnas;
	}

}
