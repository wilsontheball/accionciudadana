package ar.com.thinksoft.ac.intac.utils.collections;

public abstract class Function<F, G> {

	public G apply(F elem) {
		throw new RuntimeException();
	}

	
	public G apply(F elem1, G elem2) {
		throw new RuntimeException();
	}
}
