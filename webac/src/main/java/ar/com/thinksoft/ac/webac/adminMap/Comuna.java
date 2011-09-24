package ar.com.thinksoft.ac.webac.adminMap;

import wicket.contrib.gmap.api.GPolygon;

public class Comuna {

	private String nombreComuna;
	private GPolygon poligonoComuna;

	public Comuna(String nombre, GPolygon poligono){
		this.nombreComuna = nombre;
		this.poligonoComuna = poligono;
	}
	
	public Comuna(String nombre){
		this.nombreComuna = nombre;
	}
	
	public String getNombreComuna(){
		return this.nombreComuna;
	}
	public GPolygon getPoligono(){
		return this.poligonoComuna;
	}
}
