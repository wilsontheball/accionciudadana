package ar.com.thinksoft.ac.intac.utils.string;

public class StringUtils {

	/**
	 * No es KeySensitive
	 * Retorna si un string esta contenido en otro
	 * @param alpha
	 * @param beta
	 * @return
	 */
	public static boolean contains(String alpha, String beta) {

		try {
			return alpha.toLowerCase().contains(beta.toLowerCase());
		} catch (NullPointerException e) {
			return true;
		}

	}

}
