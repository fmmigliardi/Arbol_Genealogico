package springFinal.service;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springFinal.dto.PersonDTO;
import springFinal.model.Hombre;
import springFinal.model.Mujer;
import springFinal.model.Person;
import springFinal.model.Persona;
import springFinal.repository.Repositorio;

@Service
public class Servicio {

	@Autowired
	private Repositorio repositorio;
	public long addUser(PersonDTO dto){
		Persona test = null;
		if(dto.getSexo().equals("masculino")) {
			test = new Hombre(dto.getDni(), dto.getNombre(), dto.getApellido(), dto.getNacionalidad(),dto.getPais(), dto.getFechaNac());
		}
		if(dto.getSexo().equals("femenino")) {
			test =new Mujer(dto.getDni(),dto.getNombre(), dto.getApellido(), dto.getNacionalidad(),dto.getPais(), dto.getFechaNac());
		}
		test.setFnac(LocalDate.parse(dto.getFechaNac()));
		String padre = dto.getPadre();
		String madre = dto.getMadre();
		if (!madre.equals("unknown")) test.setMadre(repositorio.findUserByDni(madre));
		if (!padre.equals("unknown")) test.setPadre(repositorio.findUserByDni(padre));
		return repositorio.addPerson(test);
		
	}
	public List<PersonDTO> listUsers() {
		List<Persona> list = repositorio.findAll();
		return buildDTOs(list);
	}
	
	public List<PersonDTO> listMen() {
		List<Persona> list = repositorio.findAllMen();
		return buildDTOs(list);
	}
	
	public List<PersonDTO> listWomen() {
		List<Persona> list = repositorio.findAllWomen();
		return buildDTOs(list);
	}

	private List<PersonDTO> buildDTOs(List<Persona> list) {
		List<PersonDTO> dtos= new ArrayList<>();
		for(Persona person : list) {
			dtos.add(buildDTO(person));
		}
		return dtos;
	}

	private PersonDTO buildDTO(Persona invoke) {
		PersonDTO dto = new PersonDTO(invoke.getDni(),invoke.getNombre(),invoke.getApellido(), invoke.getNacionalidad(), invoke.getPais());
		if (invoke instanceof Hombre) dto.setSexo("masculino");
		else dto.setSexo("femenino");
		return dto;
	}
	
	
	public PersonDTO buildDTO(String dni) {
		return buildDTO(repositorio.findUserByDni(dni));
	}
	
	
	public List<PersonDTO> list(PersonDTO dto, String method) {
		
		method= "get"+method.substring(0, 1).toUpperCase()+method.substring(1);
		
		Persona person = repositorio.findUserByDni(dto.getDni());
		try {
			Method declaredMethod = Persona.class.getDeclaredMethod(method);
			if(declaredMethod.getReturnType().isAssignableFrom(Persona.class)) {
				Persona invoke = (Persona) declaredMethod.invoke(person);
				PersonDTO buildDTO = buildDTO(invoke);
				List<PersonDTO> buildDTOs = new ArrayList<>();
				buildDTOs.add(buildDTO);
				return buildDTOs;
			}else {
				@SuppressWarnings("unchecked")
				List<Persona> invoke = (List<Persona>) declaredMethod.invoke(person);
				return  buildDTOs(invoke);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public void list(PersonDTO dto, String method, List<PersonDTO> dtos, PersonDTO out) {
		
		method= "get"+method.substring(0, 1).toUpperCase()+method.substring(1);
		
		Persona person = repositorio.findUserByDni(dto.getDni());
		try {
			Method declaredMethod = Person.class.getDeclaredMethod(method);
			if(declaredMethod.getReturnType().isAssignableFrom(Person.class)) {
				Person invoke = (Person) declaredMethod.invoke(person);
				out.setNombre(invoke.getName());
				out.setNombre(invoke.getName());
				out.setNombre(invoke.getName());
				out.setNombre(invoke.getName());
			}else {
				@SuppressWarnings("unchecked")
				List<Persona> invoke = (List<Persona>) declaredMethod.invoke(person);
				dtos.addAll(buildDTOs(invoke));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public void updateUser(PersonDTO dto) {
		System.out.println(dto.toString());
		Persona other = repositorio.findUserByDni(dto.getDni());
		if ((dto.getNombre()!= null)) other.setNombre(dto.getNombre());
		if ((dto.getApellido()!= null)) other.setApellido(dto.getApellido());
		if (!dto.getPadre().equals(other.getPadre().getDni())) {
			if (dto.getPadre().equals("unknown")) other.resetPadre();
			else other.setPadre(repositorio.findUserByDni(dto.getPadre()));
		}
		if (!dto.getMadre().equals(other.getMadre().getDni())) {
			if (dto.getMadre().equals("unknown")) other.resetMadre();
			else other.setMadre(repositorio.findUserByDni(dto.getMadre()));
		}
		
	}
	
	public boolean containsDni(String dni) {
		if (repositorio.findAll().size()>0) {
			for (Persona a:repositorio.findAll()) {
				if (a.getDni().equals(dni))return true;
			}
		}
		return false;
	}
	
	public List<PersonDTO> findPadres(String dni){
		return buildDTOs(new ArrayList<Persona>(repositorio.findUserByDni(dni).getPadres()));
	} 
	
	public List<PersonDTO> findHermanos(String dni){
		return buildDTOs(new ArrayList<Persona>(repositorio.findUserByDni(dni).getHermanos()));
	} 
	
	public List<PersonDTO> findHermanas(String dni){
		return buildDTOs(new ArrayList<Persona>(repositorio.findUserByDni(dni).getHermanas()));
	} 
	
	public List<PersonDTO> findHijos(String dni){
		ArrayList<Persona> lista = new ArrayList<Persona>(repositorio.findUserByDni(dni).getHijos());
		for (Persona a : lista) {
			if (!repositorio.findAll().contains(a)) lista.remove(a);
		}
		return buildDTOs(lista);
	} 
	
	public List<PersonDTO> findHijas(String dni){
		ArrayList<Persona> lista = new ArrayList<Persona>(repositorio.findUserByDni(dni).getHijas());
		for (Persona a : lista) {
			if (!repositorio.findAll().contains(a)) lista.remove(a);
		}
		return buildDTOs(lista);
	} 
	
	public List<PersonDTO> findAbuelos(String dni){
		return buildDTOs(new ArrayList<Persona>(repositorio.findUserByDni(dni).getAbuelos()));
	} 
	
	public List<PersonDTO> findAbuelas(String dni){
		return buildDTOs(new ArrayList<Persona>(repositorio.findUserByDni(dni).getAbuelas()));
	} 
	
	public List<PersonDTO> findTios(String dni){
		return buildDTOs(new ArrayList<Persona>(repositorio.findUserByDni(dni).getTios()));
	} 
	
	public List<PersonDTO> findTias(String dni){
		return buildDTOs(new ArrayList<Persona>(repositorio.findUserByDni(dni).getTias()));
	} 
	
	public List<PersonDTO> findPrimos(String dni){
		return buildDTOs(new ArrayList<Persona>(repositorio.findUserByDni(dni).getPrimos()));
	} 
	
	public List<PersonDTO> findPrimas(String dni){
		return buildDTOs(new ArrayList<Persona>(repositorio.findUserByDni(dni).getPrimas()));
	} 
	
	public List<PersonDTO> findNietos(String dni){
		return buildDTOs(new ArrayList<Persona>(repositorio.findUserByDni(dni).getNietos()));
	} 
	
	public List<PersonDTO> findNietas(String dni){
		return buildDTOs(new ArrayList<Persona>(repositorio.findUserByDni(dni).getNietas()));
	} 
	
	public List<PersonDTO> findSobrinos(String dni){
		return buildDTOs(new ArrayList<Persona>(repositorio.findUserByDni(dni).getSobrinos()));
	}
	
	public List<PersonDTO> findSobrinas(String dni){
		return buildDTOs(new ArrayList<Persona>(repositorio.findUserByDni(dni).getSobrinas()));
	}
	
	
	
	
	public long getHombresNativosInRange(int a, int b){
		ArrayList<Persona> list = (ArrayList<Persona>) repositorio.findAllMen();
		List<Persona> finalList = new ArrayList<Persona>();
		for (Persona ob :list){
			if ((ob.getEdad() >= a && ob.getEdad() <= b) && (ob.getNacionalidad().equalsIgnoreCase(ob.getPais()))) {
				finalList.add(ob);
				System.out.println("N" +finalList.size());
			}
		}
		return finalList.size();
	}
	public long getMujeresNativosInRange(int a, int b){
		List<Persona> list = repositorio.findAllWomen();
		List<Persona> finalList = new ArrayList<Persona>();
		for (Persona ob :list){
			if ((ob.getEdad() >= a && ob.getEdad() <= b) && (ob.getNacionalidad().equalsIgnoreCase(ob.getPais()))) {
				finalList.add(ob);
			}
		}
		return finalList.size();
	}
	
	public long getHombresExtranjerosInRange(int a, int b){
		List<Persona> list = repositorio.findAllMen();
		List<Persona> finalList = new ArrayList<Persona>();
		for (Persona ob :list){
			if ((ob.getEdad() >= a && ob.getEdad() <= b) && (!(ob.getNacionalidad().equalsIgnoreCase(ob.getPais())))) {
				finalList.add(ob);
			}
		}
		return finalList.size();
	}
	public long getMujeresExtranjerosInRange(int a, int b){
		List<Persona> list = repositorio.findAllWomen();
		List<Persona> finalList = new ArrayList<Persona>();
		for (Persona ob :list){
			if ((ob.getEdad() >= a && ob.getEdad() <= b) && (!(ob.getNacionalidad().equalsIgnoreCase(ob.getPais())))) {
				finalList.add(ob);
			}
		}
		return finalList.size();
	}
}
