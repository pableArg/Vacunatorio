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

	public Usuario login(int dniLogin, String contrase�aLogin) {
		Usuario resultado = null;
		for (int i = 0; i < listaDeUsuarios.length; i++) {
			if (this.listaDeUsuarios[i] != null)
				if (this.listaDeUsuarios[i].getDocumento() == dniLogin
						&& this.listaDeUsuarios[i].getContrase�a().equals(contrase�aLogin)) {
					resultado = this.listaDeUsuarios[i];
				}
		}
		return resultado;
	}

	public boolean validarContrase�a(String contrase�a) {
		boolean validacionMayuscula = validacionMayuscula(contrase�a);
		boolean validacionMinuscula = validacionMinuscula(contrase�a);
		boolean validacionLongitud = validacionLongitud(contrase�a);

		boolean validacionAlMenosDosNumeros = validacionAlMenosDosNumeros(contrase�a);

		return validacionAlMenosDosNumeros && validacionLongitud && validacionMayuscula && validacionMinuscula;
	}

	private boolean validacionAlMenosDosNumeros(String contrase�a) {
		boolean validacionAlMenosDosNumeros = false;
		int contadorDeNumeros = 0;
		for (int i = 0; i < contrase�a.length(); i++) {
			char letra = contrase�a.charAt(i);
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

	private boolean validacionLongitud(String contrase�a) {
		boolean validacionLongitud = false;
		if (contrase�a.length() >= 6)
			validacionLongitud = true;
		return validacionLongitud;
	}

	private boolean validacionMinuscula(String contrase�a) {
		boolean validacionMinuscula = false;
		for (int i = 0; i < contrase�a.length(); i++) {
			char letra = contrase�a.charAt(i);
			if (Character.isLowerCase(letra) == true) {
				validacionMinuscula = true;
				break;
			}
		}
		return validacionMinuscula;
	}

	private boolean validacionMayuscula(String contrase�a) {
		boolean validacionMayuscula = false;
		for (int i = 0; i < contrase�a.length(); i++) {
			char letra = contrase�a.charAt(i);
			if (Character.isUpperCase(letra) == true) {
				validacionMayuscula = true;
				break;

			}

		}
		return validacionMayuscula;
	}

	public String generarContrase�aAleatoria() {
		String nuevaContrase�a = "";
		for (int i = 0; i < 7; i++) {
			int entero = (int) (Math.random() * (33 + 126 + 1) + 33);
			nuevaContrase�a += (char) entero;
		}

		return nuevaContrase�a;

	}

	public boolean formatoPatente(String contrase�a) {
		return contrase�a.length() == 7 && Character.isLetter(contrase�a.charAt(0))
				&& Character.isLetter(contrase�a.charAt(1)) && Character.isDigit(contrase�a.charAt(2))
				&& Character.isDigit(contrase�a.charAt(3)) && Character.isDigit(contrase�a.charAt(4))
				&& Character.isLetter(contrase�a.charAt(5)) && Character.isLetter(contrase�a.charAt(6));
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

	public Usuario cambiarContraseniaUsuario(String contrase�aNueva) {
		Usuario resultado = null;
		for (int i = 0; i < listaDeUsuarios.length; i++) {
			if (listaDeUsuarios[i] != null) {
				if (listaDeUsuarios[i].setContrase�a(contrase�aNueva)) {

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
