package ar.com.thinksoft.ac.webac;

import static org.junit.Assert.*;

import java.util.*;
import org.junit.*;

import com.db4o.ObjectSet;

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

	private List<Reclamo> lista = new ArrayList<Reclamo>();
	
	@Before
	public void SetUp(){
		ReclamoManager.getInstance().eliminarTodosReclamos();
		
		Reclamo reclamoPrueba = new Reclamo("Avellaneda",3905,50,40,new Date(),"bache","Matias","Observaciones vacias",null,new EstadoActivo(), "alta");
		
		Reclamo reclamoPrueba1 = new Reclamo("Beiro",4000,40,60,new Date(),"Caida de objetos","Rocio","Rompio la vereda",null,new EstadoEnProgreso(), "media");

		Reclamo reclamoPrueba2 = new Reclamo("Segurola",300,10,20,new Date(),"Caida de objetos ","Matias","Se cayo el balcon", null, new EstadoSuspendido(), "baja");

		lista.add(reclamoPrueba);
		lista.add(reclamoPrueba1);
		lista.add(reclamoPrueba2);
		
	}
	
	@Test
	public void guardarReclamoTest(){
		
		ReclamoManager.getInstance().guardarColeccionReclamos(this.lista);
		
		List<IReclamo> objs = Repository.getInstance().queryByExample(IReclamo.class);
		
		assertTrue(objs.size() == 3);
	}
	
	@Test
	public void obtenerTodosReclamosTest(){
		
		ReclamoManager.getInstance().guardarColeccionReclamos(this.lista);
		
		List<IReclamo> lista = ReclamoManager.getInstance().obtenerTodosReclamos();

		assertTrue(lista.size() == 3);
	}
	
	/**
	 * Problema con este test - Revisar comparacion Strings
	 * @author Matias
	 */
	@Test
	public void obtenerReclamosFiltradosPorCiudadano(){
		
		ReclamoManager.getInstance().guardarColeccionReclamos(this.lista);
		
		ObjectSet<Reclamo> listaReclamos = ReclamoManager.getInstance().obtenerReclamosFiltrados(new PredicateReclamoPorCiudadano("Matias"));
		
		assertTrue(listaReclamos.size() == 0);
		
		List<Reclamo> listaReclamos2 = ReclamoManager.getInstance().obtenerReclamosFiltrados(new PredicateReclamoPorCiudadano("Rocio"));
		
		assertTrue(listaReclamos2.size() == 0);
		
	}
	
	@Test
	public void obtenerReclamosFiltradosPorLatitudYLongitud(){
		
		ReclamoManager.getInstance().guardarColeccionReclamos(this.lista);
		
		List<Reclamo> listaReclamos = ReclamoManager.getInstance().obtenerReclamosFiltrados(new PredicateReclamoPorLatitudYLongitud(40, 60));
		
		assertTrue(listaReclamos.size() == 1);
		
	}
	
	
}
