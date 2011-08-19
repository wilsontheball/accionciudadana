package ar.com.thinksoft.ac.intac.utils.collections;

import java.util.Iterator;

public class HArrayList<E> extends java.util.ArrayList<E> {

	private static final long serialVersionUID = 4601695320135433629L;

	public <G> HArrayList<G> map(Function<E, G> f) {
		HArrayList<G> retCollection = new HArrayList<G>();
		for (E elem : this) {
			retCollection.add(f.apply(elem));
		}
		return retCollection;
	}

	public HArrayList<E> filter(Comparator<E> f) {
		HArrayList<E> retCollection = new HArrayList<E>();
		for (E elem : this) {
			if (f.apply(elem)) {
				retCollection.add(elem);
			}
		}
		return retCollection;
	}

	public E find(Comparator<E> f) {
		for (E elem : this) {
			if (f.apply(elem)) {
				return elem;
			}
		}
		throw new ElementNotFoundException();
	}

	public <G> G foldl(Function<E, G> f, G initVal) {
		G acum = initVal;
		for (E elem : this) {
			acum = f.apply(elem, acum);
		}
		return acum;
	}
}
