package ar.com.thinksoft.ac.webac.adminMap;

import java.util.ArrayList;
import java.util.List;

import ar.com.thinksoft.ac.intac.IReclamo;
import ar.com.thinksoft.ac.webac.reclamo.ReclamoManager;
import ar.com.thinksoft.ac.webac.repository.Repository;

import wicket.contrib.gmap.api.GLatLng;
import wicket.contrib.gmap.api.GPolygon;

public class ComunaManager {
	
	private List<IReclamo> reclamoList = ReclamoManager.getInstance().obtenerTodosReclamos();
	private static ComunaManager instance = null;
	private static String colorLinea = "#000000";
	private static String colorCritico = "#FF0000";
	private static String colorIntermedio = "#FFFF00";
	private static String colorPasable = "#33FF00";
	private static float opacity = Float.valueOf("0.5");
	private static List<Comuna> listaComunas = new ArrayList<Comuna>();
	
	public static ComunaManager getInstance(){
		if(instance == null){
			instance = new ComunaManager();
		}
		return instance;
	}
	
	public Comuna crearComuna(String nombre, String colorEstado, GLatLng... gLatLngs){
		GPolygon poligono = new GPolygon(getColorLinea(), 1, 1, colorEstado, getOpacity(), gLatLngs);
		return new Comuna(nombre,poligono);
	}
	
	public void guardarComunaADB(Comuna comuna){
		Repository.getInstance().store(comuna);
	}

	public Comuna obtenerComunaDesdeDB(String nombre) throws Exception{
		Comuna comunaFinder = new Comuna(nombre);
		List<Comuna> comunaList = Repository.getInstance().queryByExample(comunaFinder);
		if(comunaList.size() == 1)
			return comunaList.get(0);
		else
			throw new Exception("Comuna no encontrada");
	}
	
	
	
	/*
	 * GETTERS Y SETTERS
	 */
	public static void setColorLinea(String colorLinea) {
		ComunaManager.colorLinea = colorLinea;
	}

	public static String getColorLinea() {
		return colorLinea;
	}

	public static void setColorCritico(String colorCritico) {
		ComunaManager.colorCritico = colorCritico;
	}

	public static String getColorCritico() {
		return colorCritico;
	}

	public static void setOpacity(float opacity) {
		ComunaManager.opacity = opacity;
	}

	public static float getOpacity() {
		return opacity;
	}

	public static void setColorPasable(String colorPasable) {
		ComunaManager.colorPasable = colorPasable;
	}

	public static String getColorPasable() {
		return colorPasable;
	}

	public static void setColorIntermedio(String colorIntermedio) {
		ComunaManager.colorIntermedio = colorIntermedio;
	}

	public static String getColorIntermedio() {
		return colorIntermedio;
	}
}
