package ar.com.thinksoft.ac.webac;

import static org.junit.Assert.*;

import java.util.*;
import org.junit.*;

import ar.com.thinksoft.ac.estadosReclamo.*;
import ar.com.thinksoft.ac.intac.EnumPrioridadReclamo;
import ar.com.thinksoft.ac.intac.EnumTipoReclamo;
import ar.com.thinksoft.ac.intac.IReclamo;
import ar.com.thinksoft.ac.webac.predicates.*;
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
		
		IReclamo reclamoPrueba = new Reclamo("Avellaneda","3905","50","40",new Date(),EnumTipoReclamo.bache.getTipo(),"Matias","Observaciones vacias",null,new EstadoActivo(), EnumPrioridadReclamo.alta.getPrioridad());
		
		IReclamo reclamoPrueba1 = new Reclamo("Beiro","4000","40","60",new Date(),EnumTipoReclamo.caidaObjetos.getTipo(),"Rocio","Rompio la vereda",null,new EstadoEnProgreso(), EnumPrioridadReclamo.media.getPrioridad());
		
		IReclamo reclamoPrueba2 = new Reclamo("Segurola","300","10","20",new Date(),EnumTipoReclamo.caidaObjetos.getTipo(),"Matias","Se cayo el balcon", null, new EstadoSuspendido(), EnumPrioridadReclamo.baja.getPrioridad());
		
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
		
		List<IReclamo> lista = ReclamoManager.getInstance().obtenerReclamosFiltradosConPredicates((new PredicatePorUUID()).filtrar(this.listaReclamosTest.get(0).getId()));
		
		assertTrue(lista.size() == 1);
	}
	
	@Test
	public void obtenerReclamosPorCiudadano(){

		Reclamo reclamo = new Reclamo();
		reclamo.setCiudadanoGeneradorReclamo("Matias");
		List<IReclamo> lista = ReclamoManager.getInstance().obtenerReclamosFiltrados(reclamo);
		
		assertTrue(lista.size() == 2);
	}
	
	@Test
	public void obtenerReclamosPorLatitudYLongitud(){
		
		Reclamo reclamo = new Reclamo();
		reclamo.setLatitudIncidente("40");
		reclamo.setLongitudIncidente("60");
		List<IReclamo> lista = ReclamoManager.getInstance().obtenerReclamosFiltrados(reclamo);
	
		assertTrue(lista.size() == 1);
	}
	
	@Test
	public void obtenerReclamosPorCalle(){
		
		Reclamo reclamo = new Reclamo();
		reclamo.setCalleIncidente("Avellaneda");
		List<IReclamo> lista = ReclamoManager.getInstance().obtenerReclamosFiltrados(reclamo);
		
		assertTrue(lista.size() == 1);
	}
	
	@Test
	public void obtenerReclamosPorCalleYAltura(){
		
		Reclamo reclamo = new Reclamo();
		reclamo.setCalleIncidente("Beiro");
		reclamo.setAlturaIncidente("4000");
		List<IReclamo> lista = ReclamoManager.getInstance().obtenerReclamosFiltrados(reclamo);
		
		assertTrue(lista.size() == 1);
	}
	
	@Test
	public void obtenerReclamosPorTipo(){
		
		Reclamo reclamo = new Reclamo();
		reclamo.setTipoIncidente(EnumTipoReclamo.caidaObjetos.getTipo());
		List<IReclamo> lista = ReclamoManager.getInstance().obtenerReclamosFiltrados(reclamo);
		
		assertTrue(lista.size() == 2);
		
	}
	
	@Test
	public void obtenerReclamosPorPrioridad(){
		
		Reclamo reclamo = new Reclamo();
		reclamo.setPrioridad(EnumPrioridadReclamo.media.getPrioridad());
		List<IReclamo> lista = ReclamoManager.getInstance().obtenerReclamosFiltrados(reclamo);
		
		assertTrue(lista.size() == 1);
	}
	
	@Test
	public void obtenerReclamosPorEstado(){
		
		Reclamo reclamo = new Reclamo();
		reclamo.setEstado(new EstadoEnProgreso());
		List<IReclamo> lista = ReclamoManager.getInstance().obtenerReclamosFiltrados(reclamo);
	
		assertTrue(lista.size() == 1);
	}
	
	@Test
	public void modificarReclamo(){
		
		Reclamo reclamo = new Reclamo();
		reclamo.setPrioridad(EnumPrioridadReclamo.media.getPrioridad());
		List<IReclamo> lista = ReclamoManager.getInstance().obtenerReclamosFiltrados(reclamo);
		
		assertTrue(lista.size() == 1);
		
		for(IReclamo reclamo2 : lista){
			reclamo2.setPrioridad(EnumPrioridadReclamo.baja.getPrioridad());			
			ReclamoManager.getInstance().guardarReclamo(reclamo2);
		}
		
		assertTrue(ReclamoManager.getInstance().obtenerTodosReclamos().size() == 3);
		
		reclamo = new Reclamo();
		reclamo.setPrioridad(EnumPrioridadReclamo.baja.getPrioridad());
		assertTrue(ReclamoManager.getInstance().obtenerReclamosFiltrados(reclamo).size() == 2);
		
		
	
	}
	
}