package ar.com.thinksoft.ac.webac.procesoUnificador;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import ar.com.thinksoft.ac.intac.IReclamo;
import ar.com.thinksoft.ac.webac.exceptions.UnificadorException;
import ar.com.thinksoft.ac.webac.logging.LogFwk;
import ar.com.thinksoft.ac.webac.reclamo.ReclamoManager;
import ar.com.thinksoft.ac.webac.web.configuracion.Configuracion;

public class Unificador {

    private Timer timer;
    
    public Unificador() throws UnificadorException {
    	
		try{
			Configuracion.getInstance().cargarConfiguracion();
			timer= new Timer();
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.HOUR, Configuracion.getInstance().getHoraUnificador());
			calendar.set(Calendar.MINUTE, Configuracion.getInstance().getMinutoUnificador());
			
			int parteDelDia = 0;
			if("PM".equals(Configuracion.getInstance().getManianaOTardeUnificador()))
				parteDelDia = 1;
			
			calendar.set(Calendar.AM_PM, parteDelDia);
			Date date = calendar.getTime();
			
			//La tarea se va a ejecutar 1 hora antes, es decir, 01:00 AM
			timer.schedule(new UnificarReclamos(), date, 1000 * 60 * 60 * 24);
			
		}catch (Exception e) {
			LogFwk.getInstance(Unificador.class).error("Error durante la ejecucion del proceso unificador de reclamos. Detalle: " + e.getMessage());
			throw new UnificadorException("Error durante la ejecucion del proceso unificador de reclamos. Detalle: " + e.getMessage());
		}
    }
    
    
    public class UnificarReclamos extends TimerTask{
    	
    	private List<IReclamo> listaReclamos = new ArrayList<IReclamo>();

		public void run() {
	    	// Damos baja prioridad al hilo
	    	Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
	    	listaReclamos = ReclamoManager.getInstance().obtenerTodosReclamos();
	    	for(IReclamo reclamo:listaReclamos){
	    		//Si el reclamo es mayor a un año, lo cancelo.
	    		try {
					if(reclamoMayorAnio(reclamo))
						try {
							reclamo.cancelarReclamo();
						} catch (Exception e) {
							LogFwk.getInstance(Unificador.class).error("No se pudo cancelar reclamo durante el proceso automatico. Detalle: " + e.getMessage());
						}
				} catch (ParseException e) {
					LogFwk.getInstance(Unificador.class).error("No se pudo hacer el proceso cancelatorio de reclamos debido a una falla en una fecha");
				}
				
				//Si el reclamo no esta caido, se compara y si es posible, se unifica
				if(reclamo.isNotDown())
	    			compararReclamoConTodos(reclamo);
	    	}
    	}

		private boolean reclamoMayorAnio(IReclamo reclamo) throws ParseException {
			long millisecs_per_day = 24 * 60 * 60 * 1000;
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			Date dateReclamo;
			dateReclamo = (Date)formatter.parse(reclamo.getFechaReclamo());
			Date hoy = new Date();
			long diferencia = ( hoy.getTime() - dateReclamo.getTime() )/millisecs_per_day;
			return diferencia >= 365;
		}

		private void compararReclamoConTodos(IReclamo reclamo) {
			for(IReclamo reclamoBase: listaReclamos){
				try {
					if((reclamo.getFechaReclamo().compareTo(reclamoBase.getFechaReclamo()) <= 0))
						reclamo.unificar(reclamoBase);
					else
						reclamoBase.unificar(reclamo);
					
				} catch (Exception e) {
					LogFwk.getInstance(Unificador.class).error("No se pudo hacer el proceso unificaodr de reclamos. Detalle: " + e.getMessage());
				}
			}
		}
    }
}  


