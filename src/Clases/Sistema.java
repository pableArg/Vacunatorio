package Clases;

import java.util.Iterator;

import Interfaz.Rol;

public class Sistema {
	Usuario[] listaDeUsuarios;
	Vacuna[] listaDeVacunas;
	Turno[] listaDeTurno;
	int stock;

	public Sistema() {
		this.listaDeUsuarios = new Usuario[1000];
		Usuario administrador = new Usuario(1, "Asdasd123", "admin", Rol.Administrador, null, null);
		this.listaDeUsuarios[0] = administrador;
		Usuario paciente = new Usuario(123, "Asdasd123", "paciente", Rol.Paciente, null, null);
		this.listaDeUsuarios[1] = paciente;
		Usuario vacunador = new Usuario(4, "Asdasd123", "paciente", Rol.Vacunador, null, null);
		this.listaDeUsuarios[2] = vacunador;
		this.listaDeVacunas = new Vacuna[1000];
		this.listaDeTurno = new Turno[100];
	}

	public Boolean registrarUsuario(Usuario usuario) {
		boolean resultado = false;
		for (int i = 0; i < listaDeUsuarios.length; i++) {
			if (listaDeUsuarios[i] == null) {
				listaDeUsuarios[i] = usuario;
				resultado = true;
				break;
			}
		}
		return resultado;
	}

	public Usuario login(int dniLogin, String contraseñaLogin) {
		Usuario resultado = null;
		for (int i = 0; i < listaDeUsuarios.length; i++) {
			if (this.listaDeUsuarios[i] != null)
				if (this.listaDeUsuarios[i].getDocumento() == dniLogin
						&& this.listaDeUsuarios[i].getContraseña().equals(contraseñaLogin)) {
					resultado = this.listaDeUsuarios[i];
				}
		}
		return resultado;
	}

	public boolean validarContraseña(String contraseña) {
		boolean validacionMayuscula = validacionMayuscula(contraseña);
		boolean validacionMinuscula = validacionMinuscula(contraseña);
		boolean validacionLongitud = validacionLongitud(contraseña);

		boolean validacionAlMenosDosNumeros = validacionAlMenosDosNumeros(contraseña);

		return validacionAlMenosDosNumeros && validacionLongitud && validacionMayuscula && validacionMinuscula;
	}

	private boolean validacionAlMenosDosNumeros(String contraseña) {
		boolean validacionAlMenosDosNumeros = false;
		int contadorDeNumeros = 0;
		for (int i = 0; i < contraseña.length(); i++) {
			char letra = contraseña.charAt(i);
			if (Character.isDigit(letra) == true) {
				contadorDeNumeros++;
				if (contadorDeNumeros == 2) {
					validacionAlMenosDosNumeros = true;
					break;

				}
			}

		}
		return validacionAlMenosDosNumeros;
	}

	private boolean validacionLongitud(String contraseña) {
		boolean validacionLongitud = false;
		if (contraseña.length() >= 6)
			validacionLongitud = true;
		return validacionLongitud;
	}

	private boolean validacionMinuscula(String contraseña) {
		boolean validacionMinuscula = false;
		for (int i = 0; i < contraseña.length(); i++) {
			char letra = contraseña.charAt(i);
			if (Character.isLowerCase(letra) == true) {
				validacionMinuscula = true;
				break;
			}
		}
		return validacionMinuscula;
	}

	private boolean validacionMayuscula(String contraseña) {
		boolean validacionMayuscula = false;
		for (int i = 0; i < contraseña.length(); i++) {
			char letra = contraseña.charAt(i);
			if (Character.isUpperCase(letra) == true) {
				validacionMayuscula = true;
				break;

			}

		}
		return validacionMayuscula;
	}

	public String generarContraseñaAleatoria() {
		String nuevaContraseña = "";
		for (int i = 0; i < 7; i++) {
			int entero = (int) (Math.random() * (33 + 126 + 1) + 33);
			nuevaContraseña += (char) entero;
		}

		return nuevaContraseña;

	}

	public boolean formatoPatente(String contraseña) {
		return contraseña.length() == 7 && Character.isLetter(contraseña.charAt(0))
				&& Character.isLetter(contraseña.charAt(1)) && Character.isDigit(contraseña.charAt(2))
				&& Character.isDigit(contraseña.charAt(3)) && Character.isDigit(contraseña.charAt(4))
				&& Character.isLetter(contraseña.charAt(5)) && Character.isLetter(contraseña.charAt(6));
	}

	// METODO PARA OBTENER INFORMACION DE ALGO

	public Usuario obtenerUsuarioPorDni(int dniUsuario) {
		Usuario resultado = null;
		for (int i = 0; i < listaDeUsuarios.length; i++) {
			if (listaDeUsuarios[i] != null) {
				if (listaDeUsuarios[i].getDocumento() == dniUsuario) {
					resultado = listaDeUsuarios[i];
					break;
				}
			}
		}
		return resultado;

	}

	public boolean agregarVacuna(Vacuna vacunaNueva) {
		;
		for (int i = 0; i < listaDeVacunas.length; i++) {
			if (listaDeVacunas[i] == null)
				vacunaNueva = listaDeVacunas[i];
			return true;

		}
		return false;
	}

	public boolean asignarTurno(Turno turnoPaciente) {
		boolean resultado = false;
		for (int i = 0; i < listaDeTurno.length; i++) {
			if (listaDeTurno[i] == null) {
				turnoPaciente = listaDeTurno[i];
				resultado = true;

			}
		}
		return resultado;
	}

	public Usuario cambiarContraseniaUsuario(String contraseñaNueva) {
		Usuario resultado = null;
		for (int i = 0; i < listaDeUsuarios.length; i++) {
			if (listaDeUsuarios[i] != null) {
				if (listaDeUsuarios[i].setContraseña(contraseñaNueva)) {

				}
			}
		}
		return null;
	}

	public Boolean agregarStockVacuna(int nuevoStockVacuna) {
		if (nuevoStockVacuna > 0) {
			this.stock += nuevoStockVacuna;
		}
		return true;

	}

	public void actualizarStock(int cantidadVacuna) {
		if (cantidadVacuna < 0) {
			this.stock -= cantidadVacuna;
			if (cantidadVacuna > 0) {
				this.stock += cantidadVacuna;
			}
		}
	}

	public Turno consultarTurno(int dniPaciente) {
		Turno resultado = null;
		for (int i = 0; i < listaDeTurno.length; i++) {
			if (listaDeTurno[i] != null) {
				if (listaDeTurno[i].getDniPaciente() == dniPaciente) {
					resultado = listaDeTurno[i];
				}
			}
		}
		return resultado;

	}

}
