package ar.com.thinksoft.ac.webac.procesoUnificador;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import ar.com.thinksoft.ac.intac.EnumEstadosReclamo;
import ar.com.thinksoft.ac.intac.IReclamo;
import ar.com.thinksoft.ac.webac.logging.LogFwk;
import ar.com.thinksoft.ac.webac.reclamo.ReclamoManager;

public class Unificador {

    private Timer timer;
    
    public Unificador() {
    	
		try{
			timer= new Timer();
			Calendar calendar = Calendar.getInstance();
			// 02:00 AM = (Calendar.HOUR, 2) (Calendar.MINUTE, 0) (Calendar.AM_PM, 0);
			calendar.set(Calendar.HOUR, 2);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.AM_PM, 0);
			Date date = calendar.getTime();
			
			//La tarea se va a ejecutar 1 hora antes, es decir, 01:00 AM
			timer.schedule(new ImportarDatos(), date);
			
		}catch (Exception e) {
			LogFwk.getInstance(Unificador.class).error("Error no controlado");
		}
    }
    
    
    public class ImportarDatos extends TimerTask {
    	
    	private List<IReclamo> listaReclamos = new ArrayList<IReclamo>();

		public void run() {
	    	// Damos baja prioridad al hilo
	    	Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
	    	
	    	listaReclamos = ReclamoManager.getInstance().obtenerTodosReclamos();
	    	for(IReclamo reclamo:listaReclamos){
	    		if(isUnificable(reclamo))
	    			compararReclamoConTodos(reclamo);
	    	}
    	
    	}

		private boolean isUnificable(IReclamo reclamo) {
			return reclamo.getEstadoDescripcion() != EnumEstadosReclamo.cancelado.getEstado() &&
					 reclamo.getEstadoDescripcion() != EnumEstadosReclamo.terminado.getEstado() &&
					 reclamo.getEstadoDescripcion() != EnumEstadosReclamo.baja.getEstado();
		}

		private void compararReclamoConTodos(IReclamo reclamo) {
			for(IReclamo reclamoBase: listaReclamos){
				if(sonIguales(reclamo,reclamoBase)){
					unificar(reclamo,reclamoBase);
				}
			}
		}

		private boolean sonIguales(IReclamo reclamo, IReclamo reclamoBase) {
			return reclamo.getComunaIncidente().equals(reclamoBase.getComunaIncidente()) &&
				reclamo.getTipoIncidente().equals(reclamoBase.getTipoIncidente()) &&
				reclamo.getCalleIncidente().equals(reclamoBase.getCalleIncidente()) &&
				diferenciaAlturasMenorCienMetros(reclamo.getAlturaIncidente(),reclamoBase.getAlturaIncidente());
		}
		
		private boolean diferenciaAlturasMenorCienMetros(String alturaIncidente, String alturaIncidente2) {
			int altura1 = Integer.parseInt(alturaIncidente);
			int altura2 = Integer.parseInt(alturaIncidente2);
			
			if(altura1>altura2)
				return altura1 - altura2 < 100;
			else
				return altura2 - altura1 < 100;
		}

		private void unificar(IReclamo reclamo, IReclamo reclamoBase) {
			
		}
    }
}  


