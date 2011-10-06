package ar.com.thinksoft.ac.wilsond;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.com.thinksoft.ac.intac.EnumBarriosReclamo;
import ar.com.thinksoft.ac.intac.EnumEstadosReclamo;
import ar.com.thinksoft.ac.intac.EnumTipoReclamo;

/**
 * Representa un repositorio de reclamos. Implementa Singleton. Internamente se
 * comunica con la DB.
 * 
 * @since 24-09-2011
 * @author Paul
 */
public class Repositorio {

	// Variable de clase que almacena la unica instancia (Singleton).
	private static Repositorio instancia;

	/**
	 * Constructor privado redefiniendo el que viene por defecto (Singleton).
	 */
	private Repositorio() {
		// objectContainer = Db4oClientServer.openClient("localhost", 5555,
		// "wilson", "wilson");
		// LogFwk.getInstance(this.getClass()).info("Inicio de BD");
	}

	/**
	 * Devuelve la unica instancia de Repositorio (Singleton).
	 * 
	 * @since 24-09-2011
	 * @author Paul
	 * @return Instancia de Repositorio.
	 */
	public static Repositorio getInstancia() {

		if (instancia == null) {
			instancia = new Repositorio();
		}
		return instancia;
	}

	/**
	 * Obtiene lista de reclamos realizados por un usuario.
	 * 
	 * @since 24-09-2011
	 * @author Paul
	 * @param nombreUsuario
	 * @return Lista de reclamos.
	 */
	public List<Reclamo> getReclamos(String nombreUsuario) {

		ArrayList<Reclamo> lista = new ArrayList<Reclamo>();

		// XXX Solo para probar.......
		Reclamo rec1 = new Reclamo();
		rec1.setAlturaIncidente("145");
		rec1.setBarrioIncidente(EnumBarriosReclamo.Belgrano.getBarrio());
		rec1.setCalleIncidente("Cabildo");
		rec1.setCiudadanoGeneradorReclamo("pepe");
		rec1.setFechaReclamo(new Date().toString());
		rec1.cambiarEstado(EnumEstadosReclamo.activo.getEstado());
		rec1.setTipoIncidente(EnumTipoReclamo.accesibilidad.getTipo());
		lista.add(rec1);

		Reclamo rec3 = new Reclamo();
		rec3.setAlturaIncidente("345");
		rec3.setBarrioIncidente(EnumBarriosReclamo.Belgrano.getBarrio());
		rec3.setCalleIncidente("Cabildo");
		rec3.setCiudadanoGeneradorReclamo("pepe");
		rec3.setFechaReclamo(new Date().toString());
		rec3.cambiarEstado(EnumEstadosReclamo.cancelado.getEstado());
		rec3.setTipoIncidente(EnumTipoReclamo.bache.getTipo());
		lista.add(rec3);

		Reclamo rec4 = new Reclamo();
		rec4.setAlturaIncidente("445");
		rec4.setBarrioIncidente(EnumBarriosReclamo.Belgrano.getBarrio());
		rec4.setCalleIncidente("Cabildo");
		rec4.setCiudadanoGeneradorReclamo("pepe");
		rec4.setFechaReclamo(new Date().toString());
		rec4.cambiarEstado(EnumEstadosReclamo.demorado.getEstado());
		rec4.setTipoIncidente(EnumTipoReclamo.caidaObjetos.getTipo());
		lista.add(rec4);

		Reclamo rec5 = new Reclamo();
		rec5.setAlturaIncidente("545");
		rec5.setBarrioIncidente(EnumBarriosReclamo.Belgrano.getBarrio());
		rec5.setCalleIncidente("Cabildo");
		rec5.setCiudadanoGeneradorReclamo("pepe");
		rec5.setFechaReclamo(new Date().toString());
		rec5.cambiarEstado(EnumEstadosReclamo.enProgreso.getEstado());
		rec5.setTipoIncidente(EnumTipoReclamo.elementosViales.getTipo());
		lista.add(rec5);

		Reclamo rec6 = new Reclamo();
		rec6.setAlturaIncidente("645");
		rec6.setBarrioIncidente(EnumBarriosReclamo.Belgrano.getBarrio());
		rec6.setCalleIncidente("Cabildo");
		rec6.setCiudadanoGeneradorReclamo("pepe");
		rec6.setFechaReclamo(new Date().toString());
		rec6.cambiarEstado(EnumEstadosReclamo.suspendido.getEstado());
		rec6.setTipoIncidente(EnumTipoReclamo.inundaciones.getTipo());
		lista.add(rec6);

		Reclamo rec7 = new Reclamo();
		rec7.setAlturaIncidente("745");
		rec7.setBarrioIncidente(EnumBarriosReclamo.Belgrano.getBarrio());
		rec7.setCalleIncidente("Cabildo");
		rec7.setCiudadanoGeneradorReclamo("pepe");
		rec7.setFechaReclamo(new Date().toString());
		rec7.cambiarEstado(EnumEstadosReclamo.terminado.getEstado());
		rec7.setTipoIncidente(EnumTipoReclamo.roturaElementosPublicos.getTipo());
		lista.add(rec7);
		// XXX fin

		// XXX Solo para probar.......
		// for (int i = 0; i < 100; i++) {
		// Reclamo rec = new Reclamo();
		// rec.setAlturaIncidente("45");
		// rec.setBarrioIncidente("Belgrano");
		// rec.setCalleIncidente("Cabildo");
		// rec.setCiudadanoGeneradorReclamo("pepe");
		// rec.setFechaReclamo("hoy " + i);
		// rec.setTipoIncidente("CACA DE PERRO");
		// lista.add(rec);
		// }
		// XXX fin

		return lista;
	}

	/**
	 * Obtiene datos de perfil de un usuario.
	 * 
	 * @since 05-10-2011
	 * @author Paul
	 * @param nombreUsuario
	 * @return Perfil de usuario.
	 */
	public Usuario getPerfil(String nombreUsuario) {
		Usuario usr = new Usuario();

		// XXX por ahora siempre devuelve FRUTA!!!!!!!!!!!!!!!!
		usr.setApellido("Fulano");
		usr.setContrasenia("123");
		usr.setDni("10000000");
		usr.setMail("pepito@hot.com");
		usr.setNombre("Pepe");
		usr.setNombreUsuario("pepe");
		usr.setTelefono("456123");
		// XXX hasta aqui FRUTA!!!!!!!!!!!!!!!!
		return usr;

	}

	/**
	 * Almacena un reclamo en la base de datos.
	 * 
	 * @since 05-10-2011
	 * @author Paul
	 * @param reclamo
	 *            Reclamo a almacenar.
	 */
	public void guardarReclamo(Reclamo reclamo) {

		// XXX Solo para probar!!!!!!!!!!!!!!!!!!!!!!!!!
		System.out.println("Se recibio un RECLAMO:");

		if (reclamo == null) {
			System.out.println("reclamo es NULL");
		} else {
			System.out.println(reclamo.getAlturaIncidente());
			System.out.println(reclamo.getBarrioIncidente());
			System.out.println(reclamo.getCalleIncidente());
			System.out.println(reclamo.getCiudadanoGeneradorReclamo());
			System.out.println(reclamo.getComunaIncidente());
			System.out.println(reclamo.getEstadoDescripcion());
			System.out.println(reclamo.getFechaReclamo());
			System.out.println(reclamo.getFechaUltimaModificacionReclamo());
			System.out.println(reclamo.getId());
			System.out.println(reclamo.getLatitudIncidente());
			System.out.println(reclamo.getLongitudIncidente());
			System.out.println(reclamo.getMailCiudadanoGeneradorReclamo());
			System.out.println(reclamo.getObservaciones());
			System.out.println(reclamo.getTipoIncidente());
		}
		// XXX Solo para probar!!!!!!!!!!!!!!!!!!!!!!!!!
	}
}
