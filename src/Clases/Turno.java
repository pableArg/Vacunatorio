package Clases;

public class Turno {
	private int dia;
	private String mes;
	final int A�O = 2021;
	private int dni ;

	@Override
	public String toString() {
		return "Turno [dia=" + dia + ", mes=" + mes + ", A�O=" + A�O + ", dniPaciente=" + dni + "]";
	}

	public Turno(int dni  , int dia, String mes) {
		this.dni = dni;
		this.dia = dia;
		this.mes = mes;
	}

	public int getDniPaciente() {
		return dni;
	}

	public void setDniPaciente(int dniPaciente) {
		this.dni = dniPaciente;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public int getA�O() {
		return A�O;
	}

}
