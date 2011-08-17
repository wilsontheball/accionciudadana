package ar.com.thinksoft.ac.andrac;

/**
 * Representa informacion relevante para seguimiento de reclamo.
 * 
 * @author Paul
 * @since 11-08-2011
 */
public class ReclamoItem {
	private String estadoReclamo;
	private String tipoReclamo;
	private String direccion;
	private String fechaHora;

	// TODO falta obtener imagen
	public ReclamoItem(String estado, String tipo, String direccion,
			String fecha) {
		this.estadoReclamo = estado;
		this.tipoReclamo = tipo;
		this.direccion = direccion;
		this.fechaHora = fecha;
	}

	public String getEstado() {
		return this.estadoReclamo;
	}

	public String getTipo() {
		return this.tipoReclamo;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public String getFechaHora() {
		return this.fechaHora;
	}

	public CharSequence getResumen() {
		// TODO Falta implementar
		return this.getTipo() + "\n" + this.getDireccion() + "\n"
				+ this.getFechaHora();
	}
}
