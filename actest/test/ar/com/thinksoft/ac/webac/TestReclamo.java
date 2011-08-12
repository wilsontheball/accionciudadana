package ar.com.thinksoft.ac.webac;

import static org.junit.Assert.*;

import java.util.*;
import org.junit.*;

import ar.com.thinksoft.ac.estadosReclamo.*;
import ar.com.thinksoft.ac.intac.IReclamo;
import ar.com.thinksoft.ac.webac.predicates.*;
import ar.com.thinksoft.ac.webac.reclamo.EnumPrioridadReclamo;
import ar.com.thinksoft.ac.webac.reclamo.Reclamo;
import ar.com.thinksoft.ac.webac.reclamo.ReclamoManager;
import ar.com.thinksoft.ac.webac.repository.Repository;

/**
 * @author Matias
 */
public class TestReclamo {

	private List<IReclamo> listaReclamosTest = new ArrayList<IReclamo>();
	
	@Before
	public void SetUp(){
		ReclamoManager.getInstance().eliminarTodosReclamos();
		
		IReclamo reclamoPrueba = new Reclamo("Avellaneda",3905,50,40,new Date(),"bache","Matias","Observaciones vacias",null,new EstadoActivo(), EnumPrioridadReclamo.alta.getPrioridad());

		IReclamo reclamoPrueba1 = new Reclamo("Beiro",4000,40,60,new Date(),"Caida de objetos","Rocio","Rompio la vereda",null,new EstadoEnProgreso(), EnumPrioridadReclamo.media.getPrioridad());

		IReclamo reclamoPrueba2 = new Reclamo("Segurola",300,10,20,new Date(),"Caida de objetos","Matias","Se cayo el balcon", null, new EstadoSuspendido(), EnumPrioridadReclamo.baja.getPrioridad());

		listaReclamosTest.add(reclamoPrueba);
		listaReclamosTest.add(reclamoPrueba1);
		listaReclamosTest.add(reclamoPrueba2);
		
		ReclamoManager.getInstance().guardarColeccionReclamos(this.listaReclamosTest);
	}
	
	@Test
	public void guardarReclamoTest(){
		
		List<IReclamo> objs = Repository.getInstance().queryByExample(IReclamo.class);
		
		assertTrue(objs.size() == 3);
	}
	
	@Test
	public void obtenerTodosReclamosTest(){
		
		List<IReclamo> lista = ReclamoManager.getInstance().obtenerTodosReclamos();

		assertTrue(lista.size() == 3);
	}
	
	@Test
	public void obtenerReclamoPorUUID(){
		
		List<IReclamo> lista = ReclamoManager.getInstance().obtenerReclamosFiltrados((new PredicatePorUUID()).filtrar(this.listaReclamosTest.get(0).getId()));
		
		assertTrue(lista.size() == 1);
	}
	
	@Test
	public void obtenerReclamosPorCiudadano(){

		List<IReclamo> lista = ReclamoManager.getInstance().obtenerReclamosFiltrados((new PredicatePorCiudadano()).filtrar("Matias"));
		
		assertTrue(lista.size() == 2);
	}
	
	@Test
	public void obtenerReclamosPorLatitudYLongitud(){
		
		List<IReclamo> lista = ReclamoManager.getInstance().obtenerReclamosFiltrados((new PredicatePorLatitudYLongitud()).filtrar(40, 60));
	
		assertTrue(lista.size() == 1);
	}
	
	@Test
	public void obtenerReclamosPorCalle(){
		
		List<IReclamo> lista = ReclamoManager.getInstance().obtenerReclamosFiltrados((new PredicatePorCalle()).filtrar("Avellaneda"));
		
		assertTrue(lista.size() == 1);
	}
	
	@Test
	public void obtenerReclamosPorCalleYAltura(){
		
		List<IReclamo> lista = ReclamoManager.getInstance().obtenerReclamosFiltrados((new PredicatePorCalleYAltura()).filtrar("Beiro",4000));
		
		assertTrue(lista.size() == 1);
	}
	
	@Test
	public void obtenerReclamosPorTipo(){
		
		List<IReclamo> lista = ReclamoManager.getInstance().obtenerReclamosFiltrados((new PredicatePorTipo()).filtrar("Caida de objetos"));
		
		assertTrue(lista.size() == 2);
		
	}
	
	@Test
	public void obtenerReclamosPorPrioridad(){
		
		List<IReclamo> lista = ReclamoManager.getInstance().obtenerReclamosFiltrados((new PredicatePorPrioridad()).filtrar("media"));
		
		assertTrue(lista.size() == 1);
	}
	
	@Test
	public void obtenerReclamosPorEstado(){
		
		List<IReclamo> lista = ReclamoManager.getInstance().obtenerReclamosFiltrados((new PredicatePorEstado()).filtrar(new EstadoEnProgreso().getDescripcionEstado()));
	
		assertTrue(lista.size() == 1);
	}
	
	@Test
	public void modificarReclamo(){
	
		List<IReclamo> lista = ReclamoManager.getInstance().obtenerReclamosFiltrados((new PredicatePorPrioridad()).filtrar("media"));
		
		assertTrue(lista.size() == 1);
		
		for(IReclamo reclamo : lista){
			reclamo.setPrioridad("baja");			
			ReclamoManager.getInstance().guardarReclamo(reclamo);
		}
		
		assertTrue(ReclamoManager.getInstance().obtenerTodosReclamos().size() == 3);
		
		assertTrue(ReclamoManager.getInstance().obtenerReclamosFiltrados((new PredicatePorPrioridad()).filtrar("baja")).size() == 2);
		
		
	
	}
	
}