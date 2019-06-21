package springFinal.model;

import java.util.TreeSet;

public class Mujer extends Persona {

	public Mujer(String dni,String nombre, String apellido, String nacionalidad, String domicilio, String fnac) {
		super(dni,nombre, apellido, nacionalidad, domicilio, fnac);

	}

	public void setMadre(Persona madre) {
		super.setMadre(madre);
		this.agregarHombre(madre.getHijos());
		this.agregarMujer(madre.getHijas());
	}
	
	public void setPadre(Persona padre) {
		super.setPadre(padre);
		this.agregarHombre(padre.getHijos());
		this.agregarMujer(padre.getHijas());
	}
	
	
	public void agregarMujer(TreeSet<Persona> lista) {
		lista.add(this);
	}
	
	public void agregarHombre(TreeSet<Persona> lista) {
	}



}
