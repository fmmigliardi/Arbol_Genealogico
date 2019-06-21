package springFinal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import springFinal.dto.PersonDTO;
import springFinal.service.Servicio;

@Controller
public class Controlador {

	@Autowired
	private Servicio service;

	@GetMapping("/")
	public ModelAndView getIndex() {
		ModelAndView modelAndView = new ModelAndView("cargar");
		List<PersonDTO> maleUsers = service.listMen();
		List<PersonDTO> femaleUsers = service.listWomen();
		modelAndView.addObject("maleUsers", maleUsers);
		modelAndView.addObject("femaleUsers", femaleUsers);
		return modelAndView;
	}

	@GetMapping("/listar")
	public ModelAndView listarUsuarios() {
		ModelAndView modelAndView = new ModelAndView("listar");
		List<PersonDTO> users = service.listUsers();
		modelAndView.addObject("users", users);
		return modelAndView;
	}
	
	@PostMapping("/arbol")
	public ModelAndView mostrarArbol(PersonDTO dto) {
		ModelAndView modelAndView = new ModelAndView("relaciones");
		List<PersonDTO> padres = service.findPadres(dto.getDni());
		List<PersonDTO> hermanos = service.findHermanos(dto.getDni());
		List<PersonDTO> hermanas = service.findHermanas(dto.getDni());
		List<PersonDTO> hijos = service.findHijos(dto.getDni());
		List<PersonDTO> hijas = service.findHijas(dto.getDni());
		List<PersonDTO> abuelos = service.findAbuelos(dto.getDni());
		List<PersonDTO> abuelas = service.findAbuelas(dto.getDni());
		List<PersonDTO> tios = service.findTios(dto.getDni());
		List<PersonDTO> tias = service.findTias(dto.getDni());
		List<PersonDTO> primos = service.findPrimos(dto.getDni());
		List<PersonDTO> primas = service.findPrimas(dto.getDni());
		List<PersonDTO> nietos = service.findNietos(dto.getDni());
		List<PersonDTO> nietas = service.findNietas(dto.getDni());
		List<PersonDTO> sobrinos = service.findSobrinos(dto.getDni());
		List<PersonDTO> sobrinas = service.findSobrinas(dto.getDni());
		modelAndView.addObject("padres", padres);
		modelAndView.addObject("hermanos", hermanos);
		modelAndView.addObject("hermanas", hermanas);
		modelAndView.addObject("hijos", hijos);
		modelAndView.addObject("hijas", hijas);
		modelAndView.addObject("abuelos", abuelos);
		modelAndView.addObject("abuelas", abuelas);
		modelAndView.addObject("tios", tios);
		modelAndView.addObject("tias", tias);
		modelAndView.addObject("primos", primos);
		modelAndView.addObject("primas", primas);
		modelAndView.addObject("nietos", nietos);
		modelAndView.addObject("nietas", nietas);
		modelAndView.addObject("sobrinos", sobrinos);
		modelAndView.addObject("sobrinas", sobrinas);
		return modelAndView;
	}
	
	@PostMapping("/editar")
	public ModelAndView editarPersona(PersonDTO dto) {
		ModelAndView modelAndView = new ModelAndView("editar");
		PersonDTO edit = service.buildDTO(dto.getDni());
		List<PersonDTO> maleUsers = service.listMen();
		List<PersonDTO> femaleUsers = service.listWomen();
		
		modelAndView.addObject("maleUsers", maleUsers);
		modelAndView.addObject("femaleUsers", femaleUsers);
		modelAndView.addObject("edit",edit);
		return modelAndView;
	}

	@PostMapping("/addUser")
	public ModelAndView addUser(PersonDTO dto) {
		if (!service.containsDni(dto.getDni())) {
			service.addUser(dto);
			ModelAndView modelAndView = new ModelAndView("success");
			modelAndView.addObject("id", dto.toString());
			return modelAndView;
		}
		return new ModelAndView("error");
	}
	@PostMapping("/updateUser")
	public ModelAndView updateUser(PersonDTO dto) {
			service.updateUser(dto);
			ModelAndView modelAndView = new ModelAndView("success");
			modelAndView.addObject("id", dto.toString());
			return modelAndView;
	}
	
	@GetMapping("/piramide")
	public ModelAndView piramide(){
		ModelAndView modelAndView = new ModelAndView("piramide");
		
		long userHN9  = service.getHombresNativosInRange(0, 9);
		long userHN19 = service.getHombresNativosInRange(10, 19);
		long userHN29 = service.getHombresNativosInRange(20, 29);
		long userHN39 = service.getHombresNativosInRange(30, 39);
		long userHN49 = service.getHombresNativosInRange(40, 49);
		long userHN59 = service.getHombresNativosInRange(50, 59);
		long userHN69 = service.getHombresNativosInRange(60, 69);
		long userHN79 = service.getHombresNativosInRange(70, 130);
		
		long userHE9  = service.getHombresExtranjerosInRange(0, 9);
		long userHE19 = service.getHombresExtranjerosInRange(10, 19);
		long userHE29 = service.getHombresExtranjerosInRange(20, 29);
		long userHE39 = service.getHombresExtranjerosInRange(30, 39);
		long userHE49 = service.getHombresExtranjerosInRange(40, 49);
		long userHE59 = service.getHombresExtranjerosInRange(50, 59);
		long userHE69 = service.getHombresExtranjerosInRange(60, 69);
		long userHE79 = service.getHombresExtranjerosInRange(70, 130);
		
		long userMN9  = service.getMujeresNativosInRange(0, 9);
		long userMN19 = service.getMujeresNativosInRange(10, 19);
		long userMN29 = service.getMujeresNativosInRange(20, 29);
		long userMN39 = service.getMujeresNativosInRange(30, 39);
		long userMN49 = service.getMujeresNativosInRange(40, 49);
		long userMN59 = service.getMujeresNativosInRange(50, 59);
		long userMN69 = service.getMujeresNativosInRange(60, 69);
		long userMN79 = service.getMujeresNativosInRange(70, 130);
		
		long userME9  = service.getMujeresExtranjerosInRange(0, 9);
		long userME19 = service.getMujeresExtranjerosInRange(10, 19);
		long userME29 = service.getMujeresExtranjerosInRange(20, 29);
		long userME39 = service.getMujeresExtranjerosInRange(30, 39);
		long userME49 = service.getMujeresExtranjerosInRange(40, 49);
		long userME59 = service.getMujeresExtranjerosInRange(50, 59);
		long userME69 = service.getMujeresExtranjerosInRange(60, 69);
		long userME79 = service.getMujeresExtranjerosInRange(70, 130);
		
		modelAndView.addObject("userHN9", userHN9);
		modelAndView.addObject("userHN19", userHN19);
		modelAndView.addObject("userHN29", userHN29);
		modelAndView.addObject("userHN39", userHN39);
		modelAndView.addObject("userHN49", userHN49);
		modelAndView.addObject("userHN59", userHN59);
		modelAndView.addObject("userHN69", userHN69);
		modelAndView.addObject("userHN79", userHN79);
		
		modelAndView.addObject("userHE9", userHE9);
		modelAndView.addObject("userHE19", userHE19);
		modelAndView.addObject("userHE29", userHE29);
		modelAndView.addObject("userHE39", userHE39);
		modelAndView.addObject("userHE49", userHE49);
		modelAndView.addObject("userHE59", userHE59);
		modelAndView.addObject("userHE69", userHE69);
		modelAndView.addObject("userHE79", userHE79);
		
		modelAndView.addObject("userMN9", userMN9);
		modelAndView.addObject("userMN19", userMN19);
		modelAndView.addObject("userMN29", userMN29);
		modelAndView.addObject("userMN39", userMN39);
		modelAndView.addObject("userMN49", userMN49);
		modelAndView.addObject("userMN59", userMN59);
		modelAndView.addObject("userMN69", userMN69);
		modelAndView.addObject("userMN79", userMN79);
		
		modelAndView.addObject("userME9", userME9);
		modelAndView.addObject("userME19", userME19);
		modelAndView.addObject("userME29", userME29);
		modelAndView.addObject("userME39", userME39);
		modelAndView.addObject("userME49", userME49);
		modelAndView.addObject("userME59", userME59);
		modelAndView.addObject("userME69", userME69);
		modelAndView.addObject("userME79", userME79);
		return modelAndView;
	}
}
