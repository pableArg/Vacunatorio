package Clases;

public class Vacuna {

	private String nombre;
	private String nacionalidad;
	private int stock;
	//private Vacuna [] stockDeVacunas;
	
	public Vacuna(String nombre, String nacionalidad, int stock) {	
		this.stock = stock;
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	
	
	
}
