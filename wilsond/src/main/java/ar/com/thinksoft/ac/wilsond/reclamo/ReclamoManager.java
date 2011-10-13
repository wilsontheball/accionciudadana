package ar.com.thinksoft.ac.wilsond.reclamo;

import java.util.ArrayList;
import java.util.List;

import wicket.contrib.gmap.api.GLatLng;

import ar.com.thinksoft.ac.intac.EnumEstadosReclamo;
import ar.com.thinksoft.ac.intac.EnumPrioridadReclamo;
import ar.com.thinksoft.ac.intac.IImagen;
import ar.com.thinksoft.ac.intac.IReclamo;
import ar.com.thinksoft.ac.intac.IUsuario;
import ar.com.thinksoft.ac.webac.reclamo.Imagen;
import ar.com.thinksoft.ac.webac.reclamo.Reclamo;
import ar.com.thinksoft.ac.wilsond.GeocoderChild;
import ar.com.thinksoft.ac.wilsond.Repositorio.Repositorio;
import ar.com.thinksoft.ac.wilsond.configuracion.ConfiguracionWilsonD;
import ar.com.thinksoft.ac.wilsond.log.LogManager;
import ar.com.thinksoft.ac.wilsond.mail.MailWilsonD;

public class ReclamoManager {

	private static ReclamoManager instance;

	public ReclamoManager() {
	}

	public static ReclamoManager getInstance() {
		if (instance == null) {
			instance = new ReclamoManager();
		}
		return instance;
	}

	/**
	 * Metodo para guardar una coleccion de Reclamos. Justificacion de este
	 * metodo: si quiero guardar una coleccion de Reclamos la guarda como tal,
	 * por eso me interesa guardar cada uno de los reclamos que conforma la
	 * coleccion.
	 * 
	 * @param listaReclamos
	 * @author Matias
	 */
	public void guardarColeccionReclamos(List<IReclamo> listaReclamos) {
		for (IReclamo reclamo : listaReclamos) {
			guardarReclamo(reclamo);
		}
	}

	/**
	 * Devuelve una lista de Reclamos
	 * 
	 * @author Matias
	 */
	public List<IReclamo> obtenerTodosReclamos(String nick) {
		IReclamo reclamoFiltro = new Reclamo();
		reclamoFiltro.setCiudadanoGeneradorReclamo(nick);
		return Repositorio.getInstancia().queryByExample(reclamoFiltro);
	}

	/**
	 * Devuelve un conjunto de reclamos que cumplen con los atributos seteados
	 * en el reclamo que se pasa por parametro
	 * 
	 * @param reclamo
	 *            (type NullReclamo)
	 * @return
	 */
	public List<IReclamo> obtenerReclamosFiltrados(IReclamo reclamo) {
		return Repositorio.getInstancia().queryByExample(reclamo);
	}

	/**
	 * Almacena un reclamo en la base de datos.
	 * 
	 * @since 05-10-2011
	 * @author Paul
	 * @param reclamo
	 *            Reclamo a almacenar.
	 * @throws Exception 
	 */
	public void guardarReclamo(IReclamo reclamo) {
		Repositorio.getInstancia().store(reclamo);
		MailWilsonD.getInstance().enviarMail(reclamo.getMailCiudadanoGeneradorReclamo(), 
				"Accion Ciudadana - Activacion de reclamo", MailWilsonD.getInstance().armarTextoCambioEstados(reclamo.getEstadoDescripcion(), reclamo));
	}

	/*
	 * Traductor de lista IReclamo a lista ReclamoAndrac
	 */
	public List<ReclamoAndrac> toReclamoAndrac(List<IReclamo> list) {
		List<ReclamoAndrac> listaDefinitiva = new ArrayList<ReclamoAndrac>();

		for (IReclamo reclamoInt : list) {
			ReclamoAndrac reclamo = new ReclamoAndrac();
			reclamo.setAlturaIncidente(reclamoInt.getAlturaIncidente());
			reclamo.setBarrioIncidente(reclamoInt.getBarrioIncidente());
			reclamo.setCalleIncidente(reclamoInt.getCalleIncidente());
			reclamo.setCiudadanoGeneradorReclamo(reclamoInt
					.getCiudadanoGeneradorReclamo());
			reclamo.setMailCiudadanoGeneradorReclamo(reclamoInt
					.getMailCiudadanoGeneradorReclamo());
			reclamo.cambiarEstado(reclamoInt.getEstadoDescripcion());
			reclamo.setFechaReclamo(reclamoInt.getFechaReclamo());
			reclamo.setFechaUltimaModificacionReclamo(reclamoInt
					.getFechaUltimaModificacionReclamo());
			reclamo.setId();
			reclamo.setImagen(null);
			reclamo.setLatitudIncidente(reclamoInt.getLatitudIncidente());
			reclamo.setLongitudIncidente(reclamoInt.getLongitudIncidente());
			reclamo.setObservaciones(reclamoInt.getObservaciones());
			reclamo.setTipoIncidente(reclamoInt.getTipoIncidente());

			listaDefinitiva.add(reclamo);
		}
		return listaDefinitiva;
	}

	/*
	 * Traductor de ReclamoAndroid a ReclamoWeb
	 */
	public IReclamo toReclamoInt(IReclamo reclamoAndroid, IUsuario usuario) throws Exception {
		
		try{
			
			IReclamo reclamo = new Reclamo();
			reclamo.setId();
			
			GeocoderChild geocoder = new GeocoderChild(ConfiguracionWilsonD.getInstance().getGoogleKey());
			
			if(reclamoAndroid.getCalleIncidente()!=null & reclamoAndroid.getAlturaIncidente()!=null){
				reclamo.setCalleIncidente(reclamoAndroid.getCalleIncidente());
				reclamo.setAlturaIncidente(reclamoAndroid.getAlturaIncidente());
				
				//geoconding: calle a coordenadas
				GLatLng coordenadas = null;
				Double latitud,longitud;
				String direccion = reclamo.getCalleIncidente() + " " + reclamo.getAlturaIncidente() + ",Capital Federal";
				try {
					 coordenadas = geocoder.geocode(direccion);
					 latitud = coordenadas.getLat();
					 longitud = coordenadas.getLng();
					 reclamo.setLatitudIncidente(latitud.toString());
					 reclamo.setLongitudIncidente(longitud.toString());
				} catch (Exception e) {
					LogManager.getInstance(ReclamoManager.class).error("Problema al generar coordenadas. Detalle: " + e);
					throw new Exception("No se pudo crear reclamo" + e.getMessage());
				}
			}
			
			if(reclamoAndroid.getLatitudIncidente()!=null && reclamoAndroid.getLongitudIncidente()!=null){
				reclamo.setLatitudIncidente(reclamoAndroid.getLatitudIncidente());
				reclamo.setLongitudIncidente(reclamoAndroid.getLongitudIncidente());
			
				//geoconding: coordenadas a calle
				String coordenadas = reclamo.getLatitudIncidente() + "," + reclamo.getLongitudIncidente();
				try{
					String[] direccionFinal = geocoder.geocodeCoordenadas(coordenadas);
					reclamo.setCalleIncidente(direccionFinal[0]);
					reclamo.setAlturaIncidente(direccionFinal[1]);
					
				}catch(Exception e){
					LogManager.getInstance(ReclamoManager.class).error("Problema al generar calle y altura. Detalle: " + e);
					throw new Exception("No se pudo crear reclamo" + e.getMessage());
				}
			}
			
			reclamo.setFechaReclamo(reclamoAndroid.getFechaReclamo());
			reclamo.setFechaUltimaModificacionReclamo(reclamoAndroid.getFechaUltimaModificacionReclamo());
			
			reclamo.setCiudadanoGeneradorReclamo(usuario.getNombreUsuario());
			reclamo.setMailCiudadanoGeneradorReclamo(usuario.getMail());
			
			reclamo.setObservaciones(reclamoAndroid.getObservaciones());
			
			reclamo.setTipoIncidente(reclamoAndroid.getTipoIncidente());
			reclamo.setEstadoDescripcion(EnumEstadosReclamo.activo.getEstado());
			reclamo.setPrioridad(EnumPrioridadReclamo.noAsignada.getPrioridad());
			
			reclamo.setBarrioIncidente(reclamoAndroid.getBarrioIncidente());
			reclamo.setComunaIncidentePorBarrio(reclamoAndroid.getBarrioIncidente());
			
			IImagen imagenAndrac = reclamoAndroid.getImagen();
			IImagen imagenWebac = new Imagen(imagenAndrac.getBytes(), imagenAndrac.getContentType(), reclamo.getId());
			reclamo.setImagen(imagenWebac);
			
			return reclamo;
		
		}catch(Exception e){
			throw new Exception("No se pudo crear reclamo" + e.getMessage());
		}
		
	}
	
	

}
