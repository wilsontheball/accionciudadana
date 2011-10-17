package ar.com.thinksoft.ac.webac.web.usuario.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import ar.com.thinksoft.ac.intac.utils.collections.Comparator;
import ar.com.thinksoft.ac.intac.utils.collections.HArrayList;
import ar.com.thinksoft.ac.intac.utils.string.StringUtils;
import ar.com.thinksoft.ac.webac.predicates.registro.PredicateTodosLosUsuarios;
import ar.com.thinksoft.ac.webac.repository.Repository;
import ar.com.thinksoft.ac.webac.usuario.Usuario;
import ar.com.thinksoft.ac.webac.web.usuario.alta.UsuarioNuevoPage;

import com.inmethod.grid.DataProviderAdapter;
import com.inmethod.grid.IGridColumn;
import com.inmethod.grid.SizeUnit;
import com.inmethod.grid.column.PropertyColumn;
import com.inmethod.grid.datagrid.DefaultDataGrid;
import com.visural.wicket.component.dialog.Dialog;

public class UsuariosForm extends Form<UsuarioFilterObject> {

	private DefaultDataGrid grid;
	private UsuariosForm _self = this;
	private Dialog dialogEliminar = null;

	/**
	 * 
	 */
	private static final long serialVersionUID = 5848749487206180482L;

	@SuppressWarnings({ "serial", "rawtypes" })
	public UsuariosForm(String id) {

		super(id);
		CompoundPropertyModel<UsuarioFilterObject> model = new CompoundPropertyModel<UsuarioFilterObject>(
				new UsuarioFilterObject());
		this.setModel(model);

		this.add(new TextField<String>("campoBusquedaNombre", this.createBind(
				model, "nombre")));
		this.add(new TextField<String>("campoBusquedaApellido", this
				.createBind(model, "apellido")));
		this.add(new TextField<String>("campoBusquedaNombreUsuario", this
				.createBind(model, "nombreUsuario")));

		this.add(this.createNewButton("botonNuevo"));
		this.createTablaUsuarios("grid");
		this.add(this.grid);

		this.add(new Button("botonBuscar") {

			@Override
			public void onSubmit() {

				final UsuarioFilterObject filterObject = _self.getModelObject();

				HArrayList<Usuario> list = HArrayList
						.toHArrayList(getTodosLosUsuarios());

				List<Usuario> data = list.filter(new Comparator<Usuario>() {
					@Override
					public boolean apply(Usuario elem) {

						if (filterObject.isNull()) {
							return true;
						} else
							return StringUtils.contains(elem.getApellido(),
									filterObject.getApellido())
									|| StringUtils.contains(elem.getNombre(),
											filterObject.getNombre())
									|| StringUtils.contains(
											elem.getNombreUsuario(),
											filterObject.getNombreUsuario());

					}
				});

				grid.setDefaultModelObject(toDataProvider(data));
			}
		});
		
		dialogEliminar = new Dialog("dialogEliminar");
	    add(dialogEliminar);
	    
	    add(new AjaxLink("eliminar") {
            @Override
            public void onClick(AjaxRequestTarget target) {
			    dialogEliminar.open(target);
            }
        });
	    
	    dialogEliminar.add(new AjaxLink("eliminarUsuario"){
			@Override
			public void onClick(AjaxRequestTarget target){
				Usuario usuario = (Usuario) grid.getSelectedItems().iterator().next().getObject();
				Repository.getInstance().delete(usuario);
				dialogEliminar.close(target);
				setResponsePage(UsuarioPage.class);
				setRedirect(true);
			}
		});
	    
	    dialogEliminar.add(new AjaxLink("volver"){
	    	@Override
	    	public void onClick(AjaxRequestTarget target){
	    		dialogEliminar.close(target);
	    	}
	    });
	    
	    // FIN ELIMINAR
		
	}

	/*
	 * COMPONENTS
	 */

	private void createTablaUsuarios(String gridName) {

		ListDataProvider<Usuario> dataProvider = new ListDataProvider<Usuario>(
				this.getTodosLosUsuarios());
		List<IGridColumn> columnas = this.crearColumnas();

		DefaultDataGrid grid = new DefaultDataGrid(gridName,
				new DataProviderAdapter(dataProvider), columnas);

		grid.setRowsPerPage(10);
		grid.setClickRowToSelect(true);
		grid.setClickRowToDeselect(true);
		grid.setAllowSelectMultiple(false);
		grid.setCleanSelectionOnPageChange(true);

		this.grid = grid;
	}

	@SuppressWarnings("serial")
	private Button createNewButton(String id) {

		Button button = new Button(id) {
			@Override
			public void onSubmit() {
				setResponsePage(UsuarioNuevoPage.class);
			}
		};

		button.setDefaultFormProcessing(false);
		return button;
	}

	private List<Usuario> getTodosLosUsuarios() {
		return Repository.getInstance().query(new PredicateTodosLosUsuarios());
	}

	private List<IGridColumn> crearColumnas() {
		List<IGridColumn> columnas = new ArrayList<IGridColumn>();

		columnas.add(new PropertyColumn("apellido", new Model<String>("Apellido"), "apellido").setInitialSize(200)
																							  .setResizable(true)
																							  .setWrapText(true)
																							  .setReorderable(true)
																							  .setSizeUnit(SizeUnit.PX));
		
		columnas.add(new PropertyColumn("nombre", new Model<String>("Nombre"),"nombre").setInitialSize(200)
																					   .setResizable(true)
																					   .setWrapText(true)
																					   .setReorderable(true)
																					   .setSizeUnit(SizeUnit.PX));
		
		columnas.add(new PropertyColumn("nombreUsuario", new Model<String>("Nombre de Usuario"), "nombreUsuario").setInitialSize(200)
																												 .setResizable(true)
																												 .setWrapText(true)
																												 .setReorderable(true)
																												 .setSizeUnit(SizeUnit.PX));
		columnas.add(new PropertyColumn("dni", new Model<String>("DNI"), "dni").setInitialSize(80)
																			   .setResizable(true)
																			   .setWrapText(true)
																			   .setReorderable(true)
																			   .setSizeUnit(SizeUnit.PX));
		
		columnas.add(new PropertyColumn("mail", new Model<String>("E-Mail"),"mail").setInitialSize(250)
																				   .setResizable(true)
																				   .setWrapText(true)
																				   .setReorderable(true)
																				   .setSizeUnit(SizeUnit.PX));
		
		columnas.add(new PropertyColumn("telefono", new Model<String>("Telefono"), "telefono").setInitialSize(100)
																							  .setResizable(true)
																							  .setWrapText(true)
																							  .setReorderable(true)
																							  .setSizeUnit(SizeUnit.PX));

		return columnas;
	}

	/*
	 * AUXILIARES
	 */

	private IModel<String> createBind(
			CompoundPropertyModel<UsuarioFilterObject> model, String property) {
		return model.bind(property);
	}

	private DataProviderAdapter toDataProvider(List<Usuario> list) {
		ListDataProvider<Usuario> listDataProvider = new ListDataProvider<Usuario>(
				list);
		return new DataProviderAdapter(listDataProvider);
	}
}
