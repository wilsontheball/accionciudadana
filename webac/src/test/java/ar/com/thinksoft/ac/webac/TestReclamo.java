package ar.com.thinksoft.ac.webac;

import static org.junit.Assert.*;

import java.util.*;
import org.junit.*;

import com.db4o.ObjectSet;

import ar.com.thinksoft.ac.webac.reclamo.Reclamo;
import ar.com.thinksoft.ac.webac.reclamo.ReclamoManager;
import ar.com.thinksoft.ac.webac.repository.Repository;

public class TestReclamo {

	@Before
	public void SetUp(){
		ReclamoManager.getInstance().eliminarTodosReclamos();
	}
	
	@Test
	public void guardarReclamoTest(){
		Reclamo reclamoPrueba = new Reclamo();
		reclamoPrueba.setCiudadanoGeneradorReclamo("Matias");
		
		ReclamoManager.getInstance().guardarReclamo(reclamoPrueba);
		
		ObjectSet<Reclamo> objs = Repository.getInstance().queryByExample(Reclamo.class);
		for(Reclamo reclamo : objs){
			 System.out.println(reclamo.getCiudadanoGeneradorReclamo());
		}
		assertTrue(objs.size() == 1);
	}
	
	@Test
	public void obtenerReclamosTest(){
		Reclamo reclamoPrueba = new Reclamo();
		reclamoPrueba.setCiudadanoGeneradorReclamo("Matias");
		Reclamo reclamoPrueba1 = new Reclamo();
		reclamoPrueba1.setCiudadanoGeneradorReclamo("Rocio");
		
		ReclamoManager.getInstance().guardarReclamo(reclamoPrueba);
		ReclamoManager.getInstance().guardarReclamo(reclamoPrueba1);
		
		List<Reclamo> lista = ReclamoManager.getInstance().obtenerTodosReclamos();
		for(Reclamo reclamo : lista){
			 System.out.println(reclamo.getCiudadanoGeneradorReclamo());
		}
		assertTrue(lista.size() == 2);
	}
	
	
}
