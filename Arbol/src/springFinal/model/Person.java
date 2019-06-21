package springFinal.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Person implements Serializable{
	private static final long serialVersionUID = 2488463695989488019L;
	private String name;
	private String surname;

	public Person(String name, String surname) {
		setName(name);
		setSurname(surname);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Person getPadre() {
		return this;
	}
	
	public List<Person> getHijos(){
		ArrayList<Person> list = new ArrayList<>();
		list.add(this);
		list.add(this);
		list.add(this);
		list.add(this);
		return list;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	@Override
	public boolean equals(Object arg) {
		return isAPerson(arg) && hasTheSameName((Person)arg) && hasTheSameSurname((Person)arg);
	}

	private boolean hasTheSameSurname(Person arg) {
		return hasSurname(arg.getSurname());
	}

	private boolean hasTheSameName(Person arg) {
		return hasName(arg.getName());
	}

	private boolean isAPerson(Object arg) {
		return arg instanceof Person;
	}

	public boolean hasName(String name) {
		return getName().equalsIgnoreCase(name);
	}

	public boolean hasSurname(String surname) {
		return getSurname().equalsIgnoreCase(surname);
	}
}
