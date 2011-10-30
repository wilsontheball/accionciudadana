package ar.com.thinksoft.ac.webac.web.HomePage.Ciudadano;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.PageParameters;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.Model;

import wicket.contrib.gmap.GMap2;
import wicket.contrib.gmap.api.GLatLng;
import wicket.contrib.gmap.api.GMarker;
import ar.com.thinksoft.ac.intac.EnumEstadosReclamo;
import ar.com.thinksoft.ac.intac.IReclamo;
import ar.com.thinksoft.ac.webac.AccionCiudadanaSession;
import ar.com.thinksoft.ac.webac.exceptions.ConfiguracionException;
import ar.com.thinksoft.ac.webac.predicates.PredicatePorUUID;
import ar.com.thinksoft.ac.webac.reclamo.Reclamo;
import ar.com.thinksoft.ac.webac.reclamo.ReclamoManager;
import ar.com.thinksoft.ac.webac.web.HomePage.HomePage;
import ar.com.thinksoft.ac.webac.web.base.BasePage;
import ar.com.thinksoft.ac.webac.web.configuracion.Configuracion;

import com.inmethod.grid.DataProviderAdapter;
import com.inmethod.grid.SizeUnit;
import com.inmethod.grid.column.PropertyColumn;
import com.inmethod.grid.datagrid.DataGrid;
import com.inmethod.grid.datagrid.DefaultDataGrid;

@AuthorizeInstantiation("CIUDADANO")
public class HomePageCiudadano extends BasePage{
	
	private static String KEY = "";
	private DataGrid gridActivosCiudadano;
	private DataGrid gridUltimosModificadosCiudadano;
	
	public HomePageCiudadano(final PageParameters parameters){
		try {
			Configuracion.getInstance().cargarConfiguracion();
		} catch (ConfiguracionException e) {
			//TODO dialogo error
		}
		KEY = Configuracion.getInstance().getKeyGoogleMap();
		
		add(CSSPackageResource.getHeaderContribution(HomePage.class,"../css/Home.css"));
		
		armarGrillaActiva();
        add(gridActivosCiudadano);
        
        armarGrillaUltimosModificados();
        add(gridUltimosModificadosCiudadano);
		
		GMap2 map = crearMapaCiudadano();
		add(map);
	}
	
	private GMap2 crearMapaCiudadano() {
		GMap2 map = new GMap2("mapaCiudadano", KEY);
		map.setCenter(new GLatLng(-34.611171,-58.444176));
		map.setZoom(12);
		map.setDraggingEnabled(true);
		map.setDoubleClickZoomEnabled(true);
		map.setScrollWheelZoomEnabled(true);
		
		String usuario = ((AccionCiudadanaSession)getSession()).getUsuario().getNombreUsuario();
		IReclamo reclamoFiltro = new Reclamo();
		reclamoFiltro.setCiudadanoGeneradorReclamo(usuario);
		List<IReclamo> listReclamos = ReclamoManager.getInstance().obtenerReclamosFiltrados(reclamoFiltro);
		for(IReclamo reclamo : listReclamos){
			
			if(reclamo.isNotDown()){
				marcarReclamoEnMapa(map, reclamo);
			}else{
				if(EnumEstadosReclamo.asociado.getEstado().equals(reclamo.getEstadoDescripcion())){
					if(reclamo.getReclamoPadreId()!=null && reclamo.getReclamoPadreId() != ""){
						List<IReclamo> reclamos = ReclamoManager.getInstance().obtenerReclamosFiltradosConPredicates(new PredicatePorUUID().filtrar(reclamo.getReclamoPadreId()));
						if(reclamos.get(0).isNotDown()){
							marcarReclamoEnMapa(map, reclamo);
						}
					}
				}
			}
		}
		
		return map;
	}

	private void marcarReclamoEnMapa(GMap2 map, IReclamo reclamo) {
		if(reclamo.getLatitudIncidente()!=null && reclamo.getLongitudIncidente()!=null){
			double latitud = Double.valueOf(reclamo.getLatitudIncidente());
			double longitud = Double.valueOf(reclamo.getLongitudIncidente());
			map.addOverlay(new GMarker(new GLatLng(latitud,longitud)));
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void armarGrillaActiva() {
		String usuario = ((AccionCiudadanaSession)getSession()).getUsuario().getNombreUsuario();
		IReclamo reclamoFiltro = new Reclamo();
		reclamoFiltro.setCiudadanoGeneradorReclamo(usuario);
		List<IReclamo> listReclamos = ReclamoManager.getInstance().obtenerReclamosFiltrados(reclamoFiltro);
		List<IReclamo> listCiudadano = new ArrayList<IReclamo>();
		
		for(IReclamo reclamo : listReclamos){
			if(reclamo.isNotDown())
				listCiudadano.add(reclamo);
			else{
				if(EnumEstadosReclamo.asociado.getEstado().equals(reclamo.getEstadoDescripcion())){
					if(reclamo.getReclamoPadreId()!=null && reclamo.getReclamoPadreId() != ""){
						List<IReclamo> reclamos = ReclamoManager.getInstance().obtenerReclamosFiltradosConPredicates(new PredicatePorUUID().filtrar(reclamo.getReclamoPadreId()));
						if(reclamos.get(0).isNotDown()){
							listCiudadano.add(reclamo);
						}
					}
				}
			}
		}
		
		ListDataProvider<IReclamo> listDataProvider = new ListDataProvider<IReclamo>(listCiudadano);
		List cols = (List) Arrays.asList(
																	
            new PropertyColumn("calleCol",new Model<String>("Calle del Incidente"), "calleIncidente").setInitialSize(120)
            																						 .setResizable(false)
            																						 .setWrapText(true)
            																						 .setSizeUnit(SizeUnit.PX),
            																						 
            new PropertyColumn("alturaCol",new Model<String>("Altura"), "alturaIncidente").setInitialSize(60)
            																			  .setWrapText(true)
            																			  .setReorderable(true)
            																			  .setResizable(true)
            																			  .setSizeUnit(SizeUnit.PX),
            																			  
            new PropertyColumn("comunaCol",new Model<String>("Comuna"), "comunaIncidente").setInitialSize(80)
            																			  .setWrapText(true)
            																			  .setReorderable(true)
            																			  .setResizable(true)
            																			  .setSizeUnit(SizeUnit.PX),
            																				
            new PropertyColumn("fechaCol",new Model<String>("Fecha de alta"), "FechaReclamo").setInitialSize(100)
            																				.setReorderable(true)
            																				.setResizable(true)
            																		 		.setSizeUnit(SizeUnit.PX),
            																						
            new PropertyColumn("tipoCol",new Model<String>("Tipo"), "tipoIncidente").setInitialSize(140)
            																		.setReorderable(true)
            																		.setResizable(true)
            																		.setSizeUnit(SizeUnit.PX),
            																					 
            new PropertyColumn("prioridadCol",new Model<String>("Prioridad"), "Prioridad").setInitialSize(110)
            																				 .setReorderable(true)
            																				 .setResizable(true)
            																				 .setSizeUnit(SizeUnit.PX),
            																				 
            new PropertyColumn("estadoCol",new Model<String>("Estado"), "EstadoDescripcion").setInitialSize(110)
                 																				.setReorderable(true)
                 																				.setResizable(true)
                 																				.setSizeUnit(SizeUnit.PX)
            );
		
		gridActivosCiudadano = new DefaultDataGrid("gridCiudadano", new DataProviderAdapter(listDataProvider), cols);
		gridActivosCiudadano.setRowsPerPage(7);
        
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void armarGrillaUltimosModificados() {
		List<IReclamo> listReclamos = listaOrdenadaPorFecha();
		
		ListDataProvider<IReclamo> listDataProvider = new ListDataProvider<IReclamo>(listReclamos);
		List cols = (List) Arrays.asList(
																	
            new PropertyColumn("calleCol",new Model<String>("Calle del Incidente"), "calleIncidente").setInitialSize(120)
            																						 .setResizable(false)
            																						 .setWrapText(true)
            																						 .setSizeUnit(SizeUnit.PX),
            																						 
            new PropertyColumn("alturaCol",new Model<String>("Altura"), "alturaIncidente").setInitialSize(60)
            																			  .setWrapText(true)
            																			  .setReorderable(true)
            																			  .setResizable(true)
            																			  .setSizeUnit(SizeUnit.PX),
            																			  
            new PropertyColumn("comunaCol",new Model<String>("Comuna"), "comunaIncidente").setInitialSize(80)
            																			  .setWrapText(true)
            																			  .setReorderable(true)
            																			  .setResizable(true)
            																			  .setSizeUnit(SizeUnit.PX),
            																				
            new PropertyColumn("fechaCol",new Model<String>("Fecha de modificacion"), "FechaUltimaModificacionReclamo")
            																				.setInitialSize(140)
            																				.setReorderable(true)
            																				.setResizable(true)
            																		 		.setSizeUnit(SizeUnit.PX),
            																						
            new PropertyColumn("tipoCol",new Model<String>("Tipo"), "tipoIncidente").setInitialSize(140)
            																		.setReorderable(true)
            																		.setResizable(true)
            																		.setSizeUnit(SizeUnit.PX),
            																					 
            new PropertyColumn("prioridadCol",new Model<String>("Prioridad"), "Prioridad").setInitialSize(90)
            																				 .setReorderable(true)
            																				 .setResizable(true)
            																				 .setSizeUnit(SizeUnit.PX),
           
            new PropertyColumn("estadoCol",new Model<String>("Estado"), "EstadoDescripcion").setInitialSize(90)
             																				.setReorderable(true)
             																				.setResizable(true)
             																				.setSizeUnit(SizeUnit.PX)
            );
		
		gridUltimosModificadosCiudadano = new DefaultDataGrid("gridUltimosModificadosCiudadano", new DataProviderAdapter(listDataProvider), cols);
		gridUltimosModificadosCiudadano.setRowsPerPage(7);
        
	}

	/*
	 * Lista ordenada por fecha de modificacion = lista invertida, ya que al modificar un reclamo se
	 * esta creando uno nuevo clonado y los atributos actualizados.
	 */
	private List<IReclamo> listaOrdenadaPorFecha() {
		List<IReclamo> lista = ReclamoManager.getInstance().obtenerTodosReclamos();
		List<IReclamo> listaDevolucion = new ArrayList<IReclamo>();
		for(int i = lista.size()-1 ;i>=0;i--){
			if(lista.get(i).getCiudadanoGeneradorReclamo().equals(((AccionCiudadanaSession)getSession()).getUsuario().getNombreUsuario()))
				listaDevolucion.add(lista.get(i));
		}
		return listaDevolucion;
	
	}
}
