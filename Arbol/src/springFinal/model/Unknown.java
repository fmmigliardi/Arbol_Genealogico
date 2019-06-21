package springFinal.model;

import java.util.Collection;
import java.util.TreeSet;

class Unknown extends Persona {

	
	
	public Unknown() {

	}

	@Override
	public void addIfNotUnknow(Collection<Persona> collection) {
	}
	
	public int compareTo(Persona o) {
		return 1;
	}

}