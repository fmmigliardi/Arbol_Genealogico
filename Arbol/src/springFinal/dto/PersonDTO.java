package springFinal.dto;

public class PersonDTO {

	private String nombre;
	private String apellido;
	private String dni;
  	private String fechaNac;
	private boolean murio;
	private String fechaFall;
	private String nacionalidad;
	private String sexo;
	private String padre;
	private String madre;
	private String pais;
	private String ciudad;
	private String direccion;

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public PersonDTO() {
	}

	public PersonDTO(String dni,String nombre, String apellido, String nacionalidad, String pais) {
		setDni(dni);
		setNombre(nombre);
		setApellido(apellido);
		setNacionalidad(nacionalidad);
		setPais(pais);
	}

	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public String getDni() {
		return dni;
	}


	public void setDni(String dni) {
		this.dni = dni;
	}


	public String getFechaNac() {
		return fechaNac;
	}


	public void setFechaNac(String fechaNac) {
		this.fechaNac = fechaNac;
	}


	public boolean isMurio() {
		return murio;
	}


	public void setMurio(boolean murio) {
		this.murio = murio;
	}


	public String getFechaFall() {
		return fechaFall;
	}


	public void setFechaFall(String fechaFall) {
		this.fechaFall = fechaFall;
	}


	public String getNacionalidad() {
		return nacionalidad;
	}


	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getMadre() {
		return madre;
	}


	public void setMadre(String madre) {
		this.madre = madre;
	}

	public String getSexo() {
		return sexo;
	}


	public void setSexo(String sexo) {
		this.sexo = sexo;
	}


	public String getPadre() {
		return padre;
	}


	public void setPadre(String padre) {
		this.padre = padre;
	}
	
	public String getFullNombre() {
		return getNombre() + " " + getApellido();
	}

	@Override
	public String toString() {
		return "PersonDTO [nombre=" + nombre + ", apellido=" + apellido + ", dni=" + dni + ", fechaNac=" + fechaNac
				+ ", murio=" + murio + ", fechaFall=" + fechaFall + ", nacionalidad=" + nacionalidad + ", sexo=" + sexo
				+ ", padre=" + padre + ", madre=" + madre + ", pais=" + pais + ", ciudad=" + ciudad + ", direccion=" + direccion + "]";
	}

}