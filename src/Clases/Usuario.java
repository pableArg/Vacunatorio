package Clases;

import Interfaz.Rol;

public class Usuario {
	private int documento;
	private String nombre;

	@Override
	public String toString() {
		return "Usuario documento=" + documento + ", nombre=" + nombre + ", contrase�a=" + contrase�a + "]";
	}

	private String contrase�a;
	private Rol rol;
	private Vacuna primeraDosis;
	private Vacuna segundaDosis;

	public Usuario(int documento, String contrase�a, String nombre, Rol rol, Vacuna dosis1, Vacuna dosis2) {
		this.documento = documento;
		this.contrase�a = contrase�a;
		this.nombre = nombre;
		this.rol = rol;
		this.primeraDosis = dosis1;
		this.segundaDosis = dosis2;

	}

	public String getContrase�a() {
		return contrase�a;
	}

	public boolean setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
		return false;
	}

	public int getDocumento() {
		return documento;
	}

	public void setDocumento(int documento) {
		this.documento = documento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public String consultarVacunasAplicadas() {
		String retorno = "";
		// no tiene dosis
		if (this.primeraDosis == null && this.segundaDosis == null) {
			retorno = "No tiene dosis aplicadas";
		} else {
			if (this.primeraDosis != null) {
				retorno += "tiene la primera dosis : " + this.primeraDosis.toString();
			}
			if (this.segundaDosis != null) {
				retorno += "tiene la segunda dosis : " + this.segundaDosis.toString();
			}

		}
		return retorno;

	}

	public void vacunar(Vacuna vacuna) {
		if (this.primeraDosis != null && this.segundaDosis != null) {
			return;
		}
		if (this.primeraDosis == null) {
			this.primeraDosis = vacuna;
		} else {
			this.segundaDosis = vacuna;
		}
	}

	public boolean setAplciarDosisUno(Vacuna vacuna) {
		this.primeraDosis = vacuna;
		return true;
	}

	public void setAplciarDosisDos(Vacuna vacuna) {
		this.segundaDosis = vacuna;
	}

	public static boolean consultarTurno(int dniConsulta) {
		return false;

	}
	

}
