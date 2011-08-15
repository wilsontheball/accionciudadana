package ar.com.thinksoft.ac.andrac;

/**
 * Representa informacion relevante para seguimiento de reclamo.
 * 
 * @author Paul
 * @since 11-08-2011
 */
public class ReclamoItem {
	private String titulo;
	private String subtitulo;

	public ReclamoItem(String tit, String sub) {
		titulo = tit;
		subtitulo = sub;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getSubtitulo() {
		return subtitulo;
	}

	public CharSequence getResumen() {
		// TODO Falta implementar
		return this.getTitulo() + " " + this.getSubtitulo();
	}
}
