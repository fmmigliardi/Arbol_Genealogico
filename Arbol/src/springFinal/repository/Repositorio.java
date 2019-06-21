package springFinal.repository;


import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

import springFinal.model.Hombre;
import springFinal.model.Mujer;
import springFinal.model.Persona;

@Repository
public class Repositorio {
	
	private List<Persona> personas=new ArrayList<>();

	public long addPerson(Persona person) {
		getPersonas().add(person);
		return getPersonas().size() -1;
	}
	
	private List<Persona> getPersonas() {
		return personas;
	}
	
	public List<Persona> findAllMen(){
		List<Persona> list = new ArrayList<Persona>();
		for (Persona a:getPersonas()) {
			if (a instanceof Hombre) list.add(a);
		}
		return list;
	}
	
	public List<Persona> findAllWomen(){
		List<Persona> list = new ArrayList<Persona>();
		for (Persona a:getPersonas()) {
			if (a instanceof Mujer) list.add(a);
		}
		return list;
	}
	public void setPersons(List<Persona> personas) {
		this.personas = personas;
	}

	public List<Persona> findAll() {
		return new ArrayList<>(getPersonas());
	}
	
	public Persona findUserByDni(String dni) {
		for(Persona persona : getPersonas()) {
			if(persona.getDni().equals(dni)) return persona;
		}
		return null;

	}
	public void removeUserByDni(String dni) {
		if (getPersonas().size()>0) {
			for(Persona persona : getPersonas()) {
				if(persona.getDni().equals(dni)) getPersonas().remove(persona);
			}
		}
	}
}
