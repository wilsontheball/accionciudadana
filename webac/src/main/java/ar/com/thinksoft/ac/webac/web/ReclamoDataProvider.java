package ar.com.thinksoft.ac.webac.web;

import java.util.Iterator;

import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;

import ar.com.thinksoft.ac.intac.IReclamo;
import ar.com.thinksoft.ac.webac.reclamo.ReclamoManager;

@SuppressWarnings("serial")
public class ReclamoDataProvider implements IDataProvider<IReclamo>{

	private IModel<IReclamo> model;

	public ReclamoDataProvider(IModel<IReclamo> model){
		this.model = model;
	}
	
	public void detach() {
	}

	public Iterator<IReclamo> iterator(int first, int count) {
		return ReclamoManager.getInstance().obtenerTodosReclamos().iterator();
	}

	public int size() {
		return ReclamoManager.getInstance().obtenerTodosReclamos().size();
	}

	public IModel<IReclamo> model(IReclamo object) {
		return this.model;
	}

}
