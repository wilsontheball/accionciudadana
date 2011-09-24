package ar.com.thinksoft.ac.webac.web.HomePage.Administrativo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.JavascriptPackageResource;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.Model;

import com.inmethod.grid.DataProviderAdapter;
import com.inmethod.grid.SizeUnit;
import com.inmethod.grid.column.PropertyColumn;
import com.inmethod.grid.datagrid.DataGrid;
import com.inmethod.grid.datagrid.DefaultDataGrid;

import wicket.contrib.gmap.GMap2;
import wicket.contrib.gmap.api.GLatLng;


import ar.com.thinksoft.ac.intac.IPermiso;
import ar.com.thinksoft.ac.intac.IReclamo;
import ar.com.thinksoft.ac.webac.adminMap.Comuna;
import ar.com.thinksoft.ac.webac.adminMap.ComunaManager;
import ar.com.thinksoft.ac.webac.predicates.PredicatePorEstado;
import ar.com.thinksoft.ac.webac.reclamo.ReclamoManager;
import ar.com.thinksoft.ac.webac.web.HomePage.HomePage;
import ar.com.thinksoft.ac.webac.web.base.BasePage;
import ar.com.thinksoft.webac.comparador.FechaModificacionComparator;

public class HomePageAdministrativo extends BasePage{
	
	private static String KEY = "ABQIAAAASNhk0DNhWwkPk0Y12RIrThTwM0brOpm-All5BF6PoaKBxRWWERRi58__PuwPgysGGKPkLxYHu8hULg";
	private DataGrid gridActivos;
	private DataGrid gridUltimosModificados;
	
	@Override
	public IPermiso getPermisoNecesario() {
		return new HomePageAdministrativoPermiso();
	}
	
	public HomePageAdministrativo(final PageParameters parameters){
		add(CSSPackageResource.getHeaderContribution(HomePage.class,"../css/HomeAdministrativo.css"));
		add(JavascriptPackageResource.getHeaderContribution(HomePage.class,"../js/homeAdministrativo.js"));
		
		armarGrillaActiva();
        add(gridActivos);
        
        armarGrillaUltimosModificados();
        add(gridUltimosModificados);
		
		GMap2 map = crearMapaAdministrativo();
		add(map);
	}

	private GMap2 crearMapaAdministrativo() {
		GMap2 map = new GMap2("mapa", KEY);
		map.setCenter(new GLatLng(-34.611171,-58.444176));
		map.setZoom(12);
		map.setDraggingEnabled(false);
		ComunaManager comunaManager = new ComunaManager();
		for(Comuna comuna : comunaManager.getListaComunas()){
			map.addOverlay(comuna.getPoligono());
		}
		return map;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void armarGrillaActiva() {
		List<IReclamo> listReclamos = ReclamoManager.getInstance().obtenerReclamosFiltradosConPredicates(new PredicatePorEstado().isNotDownFiltro());
		ListDataProvider<IReclamo> listDataProvider = new ListDataProvider<IReclamo>(listReclamos);
		List cols = (List) Arrays.asList(
																	
            new PropertyColumn("calleCol",new Model<String>("Calle del Incidente"), "calleIncidente").setInitialSize(200)
            																						 .setResizable(false)
            																						 .setWrapText(true)
            																						 .setSizeUnit(SizeUnit.PX),
            																						 
            new PropertyColumn("alturaCol",new Model<String>("Altura"), "alturaIncidente").setInitialSize(50)
            																			  .setWrapText(true)
            																			  .setReorderable(true)
            																			  .setResizable(true)
            																			  .setSizeUnit(SizeUnit.PX),
            																			  
            new PropertyColumn("comunaCol",new Model<String>("Comuna"), "comunaIncidente").setInitialSize(80)
            																			  .setWrapText(true)
            																			  .setReorderable(true)
            																			  .setResizable(true)
            																			  .setSizeUnit(SizeUnit.PX),
            																				
            new PropertyColumn("fechaCol",new Model<String>("Fecha de alta"), "FechaReclamo").setInitialSize(80)
            																				.setReorderable(true)
            																				.setResizable(true)
            																		 		.setSizeUnit(SizeUnit.PX),
            																						
            new PropertyColumn("tipoCol",new Model<String>("Tipo"), "tipoIncidente").setInitialSize(130)
            																		.setReorderable(true)
            																		.setResizable(true)
            																		.setSizeUnit(SizeUnit.PX),
            																					 
            new PropertyColumn("prioridadCol",new Model<String>("Prioridad"), "Prioridad").setInitialSize(80)
            																				 .setReorderable(true)
            																				 .setResizable(true)
            																				 .setSizeUnit(SizeUnit.PX)
            );
		
		gridActivos = new DefaultDataGrid("grid", new DataProviderAdapter(listDataProvider), cols);
		gridActivos.setRowsPerPage(10);
        gridActivos.setClickRowToSelect(true);
        gridActivos.setAllowSelectMultiple(true);
        gridActivos.setClickRowToDeselect(true);
        gridActivos.setCleanSelectionOnPageChange(false);
        
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void armarGrillaUltimosModificados() {
		List<IReclamo> listReclamos = ReclamoManager.getInstance().obtenerReclamosFiltradosConPredicates(new PredicatePorEstado().isNotDownFiltro());
		//Collections.sort(listReclamos,new FechaModificacionComparator());
		
		ListDataProvider<IReclamo> listDataProvider = new ListDataProvider<IReclamo>(listReclamos);
		List cols = (List) Arrays.asList(
																	
            new PropertyColumn("calleCol",new Model<String>("Calle del Incidente"), "calleIncidente").setInitialSize(200)
            																						 .setResizable(false)
            																						 .setWrapText(true)
            																						 .setSizeUnit(SizeUnit.PX),
            																						 
            new PropertyColumn("alturaCol",new Model<String>("Altura"), "alturaIncidente").setInitialSize(50)
            																			  .setWrapText(true)
            																			  .setReorderable(true)
            																			  .setResizable(true)
            																			  .setSizeUnit(SizeUnit.PX),
            																			  
            new PropertyColumn("comunaCol",new Model<String>("Comuna"), "comunaIncidente").setInitialSize(80)
            																			  .setWrapText(true)
            																			  .setReorderable(true)
            																			  .setResizable(true)
            																			  .setSizeUnit(SizeUnit.PX),
            																				
            new PropertyColumn("fechaCol",new Model<String>("Fecha de alta"), "FechaReclamo").setInitialSize(80)
            																				.setReorderable(true)
            																				.setResizable(true)
            																		 		.setSizeUnit(SizeUnit.PX),
            																						
            new PropertyColumn("tipoCol",new Model<String>("Tipo"), "tipoIncidente").setInitialSize(130)
            																		.setReorderable(true)
            																		.setResizable(true)
            																		.setSizeUnit(SizeUnit.PX),
            																					 
            new PropertyColumn("prioridadCol",new Model<String>("Prioridad"), "Prioridad").setInitialSize(80)
            																				 .setReorderable(true)
            																				 .setResizable(true)
            																				 .setSizeUnit(SizeUnit.PX)
            );
		
		gridUltimosModificados = new DefaultDataGrid("gridUltimosModificados", new DataProviderAdapter(listDataProvider), cols);
		gridUltimosModificados.setRowsPerPage(10);
		gridUltimosModificados.setClickRowToSelect(true);
		gridUltimosModificados.setAllowSelectMultiple(true);
		gridUltimosModificados.setClickRowToDeselect(true);
		gridUltimosModificados.setCleanSelectionOnPageChange(false);
        
	}
}
