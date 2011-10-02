package ar.com.thinksoft.ac.webac.procesoUnificador;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import ar.com.thinksoft.ac.intac.IReclamo;
import ar.com.thinksoft.ac.webac.logging.LogFwk;
import ar.com.thinksoft.ac.webac.reclamo.ReclamoManager;
import ar.com.thinksoft.ac.webac.web.configuracion.Configuracion;

public class Unificador {

    private Timer timer;
    
    public Unificador() {
    	
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
			LogFwk.getInstance(Unificador.class).error("Error no controlado");
		}
    }
    
    
    public class UnificarReclamos extends TimerTask {
    	
    	private List<IReclamo> listaReclamos = new ArrayList<IReclamo>();

		public void run() {
	    	// Damos baja prioridad al hilo
	    	Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
	    	listaReclamos = ReclamoManager.getInstance().obtenerTodosReclamos();
	    	for(IReclamo reclamo:listaReclamos){
	    		if(reclamo.isNotDown())
	    			compararReclamoConTodos(reclamo);
	    	}
    	}

		private void compararReclamoConTodos(IReclamo reclamo) {
			for(IReclamo reclamoBase: listaReclamos){
				if((reclamo.getFechaReclamo().compareTo(reclamoBase.getFechaReclamo()) <= 0))
					reclamo.unificar(reclamoBase);
				else
					reclamoBase.unificar(reclamo);
			}
		}
    }
}  


