package ar.com.thinksoft.ac.webac;

import static org.junit.Assert.*;

import java.util.*;
import org.junit.*;

import ar.com.thinksoft.ac.estadosReclamo.*;
import ar.com.thinksoft.ac.intac.IReclamo;
import ar.com.thinksoft.ac.webac.predicates.PredicateReclamoPorCiudadano;
import ar.com.thinksoft.ac.webac.predicates.PredicateReclamoPorLatitudYLongitud;
import ar.com.thinksoft.ac.webac.reclamo.Reclamo;
import ar.com.thinksoft.ac.webac.reclamo.ReclamoManager;
import ar.com.thinksoft.ac.webac.repository.Repository;

/**
 * @author Matias
 */
public class TestReclamo {

	private List<IReclamo> lista = new ArrayList<IReclamo>();
	
	@Before
	public void SetUp(){
		ReclamoManager.getInstance().eliminarTodosReclamos();
		
		Reclamo reclamoPrueba = new Reclamo("Avellaneda",3905,50,40,new Date(),"bache","Matias","Observaciones vacias",null,new EstadoActivo());
		
		Reclamo reclamoPrueba1 = new Reclamo("Beiro",4000,40,60,new Date(),"Caida de objetos","Rocio","Rompio la vereda",null,new EstadoEnProgreso());

		Reclamo reclamoPrueba2 = new Reclamo("Segurola",300,10,20,new Date(),"Caida de objetos ","Matias","Se cayo el balcon", null, new EstadoSuspendido());

		lista.add(reclamoPrueba);
		lista.add(reclamoPrueba1);
		lista.add(reclamoPrueba2);
		
	}
	
	@Test
	public void guardarReclamoTest(){
		
		ReclamoManager.getInstance().guardarColeccionReclamos(this.lista);
		
		List<IReclamo> objs = Repository.getInstance().queryByExample(IReclamo.class);
		/*for(IReclamo reclamo : objs){
			 System.out.println(reclamo.getCiudadanoGeneradorReclamo());
		}*/
		assertTrue(objs.size() == 3);
	}
	
	@Test
	public void obtenerTodosReclamosTest(){
		
		ReclamoManager.getInstance().guardarColeccionReclamos(this.lista);
		
		List<IReclamo> lista = ReclamoManager.getInstance().obtenerTodosReclamos();
		/*for(IReclamo reclamo : lista){
			 System.out.println(reclamo.getCiudadanoGeneradorReclamo());
		}*/
		assertTrue(lista.size() == 3);
	}
	
	/**
	 * Problema con este test - Revisar comparación Strings
	 * @author Matias
	 */
	@Test
	public void obtenerReclamosFiltradosPorCiudadano(){
		
		ReclamoManager.getInstance().guardarColeccionReclamos(this.lista);
		
		Reclamo reclamoFiltro = new Reclamo();
		reclamoFiltro.setCiudadanoGeneradorReclamo("Matias");
		
		List<Reclamo> listaReclamos = (ReclamoManager.getInstance().obtenerReclamosFiltrados(new PredicateReclamoPorCiudadano(reclamoFiltro)));
		
		for(Reclamo reclamo : listaReclamos){
		 	System.out.println(reclamo.getCiudadanoGeneradorReclamo());
		}
		assertTrue(listaReclamos.size() == 0);
		
		Reclamo reclamoFiltro2 = new Reclamo();
		reclamoFiltro2.setCiudadanoGeneradorReclamo("Rocio");
		
		List<Reclamo> listaReclamos2 = (ReclamoManager.getInstance().obtenerReclamosFiltrados(new PredicateReclamoPorCiudadano(reclamoFiltro2)));
		
		for(Reclamo reclamo : listaReclamos2){
		 	System.out.println(reclamo.getCiudadanoGeneradorReclamo());
		}
		assertTrue(listaReclamos2.size() == 0);
		
	}
	
	@Test
	public void obtenerReclamosFiltradosPorLatitudYLongitud(){
		
		ReclamoManager.getInstance().guardarColeccionReclamos(this.lista);
		
		Reclamo reclamoFiltro = new Reclamo();
		reclamoFiltro.setLatitudIncidente(50);
		reclamoFiltro.setLongitudIncidente(40);
		
		List<Reclamo> listaReclamos = (ReclamoManager.getInstance().obtenerReclamosFiltrados(new PredicateReclamoPorLatitudYLongitud(reclamoFiltro)));
		
		assertTrue(listaReclamos.size() == 1);
		
	}
	
	
}
