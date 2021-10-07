package Interfaz;

import java.util.Scanner;

import Clases.Usuario;
import Clases.Vacuna;
import Clases.Sistema;
import Clases.Turno;

public class InterfazVacuna {
	static Scanner teclado = new Scanner(System.in);
	static Sistema vacunate;
	static Usuario usuario;
	final static int REGISTRAR_VACUNADOR = 1;
	final static int AGREGAR_VACUNA_NUEVA = 3;
	final static int AGREGAR_STOCK_VACUNAS = 2;
	final static int ADMINISTRAR_VACUNAS = 1;
	final static int ADMINISTRAR_TURNOS = 2;
	final static int ASIGNAR_TURNO = 1;
	final static int CONSULTAR_TURNO = 2;
	final static int CAMBIAR_DATOS = 1;
	final static int CANCELAR_TURNO = 3;
	final static int CONSULTA_VACUNAS_APLICADAS = 4;
	final static int CONSULTAR_TURNO_PACIENTE = 1;
	final static int APLICAR_VACUNA = 2;
	final static int CONSULTAR_STOCK = 3;
	final static int APLICAR_PRIMERA_DOSIS = 1;
	final static int APLICAR_SEGUNDA_DOSIS = 2;

	public static void main(String[] args) {
		int opcionDeseada;
		final int REGISTRAR_USUARIO = 1;
		final int LOGUEAR = 2;
		final int SALIR = 9;

		vacunate = new Sistema();
		usuario = new Usuario(1, "", "", Rol.Paciente, null, null);

		do {
			opcionDeseada = menuPrincipal();

			switch (opcionDeseada) {
			case REGISTRAR_USUARIO:
				menuRegistrar();
				break;

			case LOGUEAR:
				Usuario usuarioLogeado = Loguear();
				switch (usuarioLogeado.getRol()) {
				case Administrador:

					int opcionDesada = menuPrincipalAdministrador();
					switch (opcionDesada) {
					case ADMINISTRAR_VACUNAS:

						int opcionDeseadaVacunas = menuVacunasAdministrador();
						switch (opcionDeseadaVacunas) {
						case REGISTRAR_VACUNADOR:
							registrarVacunadorAdministrador();
							break;

						case AGREGAR_STOCK_VACUNAS:
							agregarStockDeVacunas();
							break;

						case AGREGAR_VACUNA_NUEVA:
							registrarVacunaAdministrador();
							break;

						default:
							System.out.println("Opcion Incorrecta");
							break;
						}

						break;

					case ADMINISTRAR_TURNOS:

						int opciondeseadaTurno = menuAdministradorTurno();
						switch (opciondeseadaTurno) {
						case ASIGNAR_TURNO:
							asignarTurnoAdministrador();

							break;

						case 2:
							break;

						default:
							break;
						}

						break;
					default:
						break;
					}
					break;

				case Vacunador:
					System.out.println("1. consultar turno de paciente");
					System.out.println("2. aplicar Vacuna");
					System.out.println("3. consultar Stock de vacunas");
					int opcionDeseadaVacunador = teclado.nextInt();
					switch (opcionDeseadaVacunador) {
					case CONSULTAR_TURNO_PACIENTE:
						System.out.println("INGRESE EL DNI DEL PACIENTE A CONSULTAR");
						int dniPaciente = teclado.nextInt();
						Usuario obtenerPaciente = vacunate.obtenerUsuarioPorDni(dniPaciente);
						if (vacunate.consultarTurno(dniPaciente) != null) {
							System.out.println("No posee turno");
						} else {
							System.out.println(vacunate.consultarTurno(dniPaciente));
						}

						break;

					case APLICAR_VACUNA:
						System.out.println("1. aplicar primera dosis");
						System.out.println("2. aplicar segunda dosis");
						int opcionAplicarVacuna = teclado.nextInt();
						switch (opcionAplicarVacuna) {

						case APLICAR_PRIMERA_DOSIS:
							System.out.println("Ingrese el dni del paciente a vacunar");
							int dni = teclado.nextInt();
							Usuario pacienteObtenido = vacunate.obtenerUsuarioPorDni(dni);
							if (pacienteObtenido != null) {
								System.out.println("Ingrese nombre de la vacuna aplicar");
								String nombreVacuna = teclado.next();
								System.out.println("Ingrese la nacionalidad");
								String nacionalidadVacuna = teclado.next();
								 Vacuna nuevaVacuna = new Vacuna (nombreVacuna,nacionalidadVacuna , 1);
								boolean aplicoDosis = usuario.setAplciarDosisUno(nuevaVacuna);
								if(aplicoDosis == true)
									System.out.println("Dosis aplicada con exito");
							}else {
								System.out.println("No se pudo obtener el paciente.");
							}

							break;

						case APLICAR_SEGUNDA_DOSIS:
							break;

						default:
							break;
						}
						break;

					case CONSULTAR_STOCK:
						break;

					default:
						break;
					}

					break;

				case Paciente:
					System.out.println("1. INGRESE PARA CAMBIAR DATOS DE SU CUENTA");
					System.out.println("2. PARA CONSULTAR TU TURNO ASIGNADO");
					System.out.println("3. PARA CANCELAR TURNO");
					System.out.println("4. PARA CONSULTAR VACUNAS APLICADAS");
					int opcionDeseadaPaciente = teclado.nextInt();
					switch (opcionDeseadaPaciente) {
					case CAMBIAR_DATOS:
						System.out.println("1. PARA CAMBIAR SU CONTRASEÑA");
						int opcionCambioContraseña = teclado.nextInt();
						switch (opcionCambioContraseña) {
						case 1:
							cambiarContraseña();

							break;

						default:
							System.out.println("Opcion incorrecta");
							break;
						}
						break;

					case CONSULTAR_TURNO:
						System.out.println("Ingrese su dni ");
						int dniConsulta = teclado.nextInt();
						boolean consultoTurno = Usuario.consultarTurno(dniConsulta);
						System.out.println();

						break;

					case CANCELAR_TURNO:
						break;

					case CONSULTA_VACUNAS_APLICADAS:

						break;

					default:
						System.out.println("Opcion incorrecta");
						break;
					}

				default:
					break;
				}

				break;

			default:
				System.out.println("OPCION INCORRECTA");
				break;
			}

		} while (opcionDeseada != SALIR);
		System.out.println("ADIOS");
	}

	private static void cambiarContraseña() {
		for (int i = 0; i < 3; i++) {

			System.out.println("INGRESE SU NUEVA CONTRASEÑA");
			String nuevaContraseña = teclado.next();
			boolean contraseñaValidada = vacunate.validarContraseña(nuevaContraseña);
			if (contraseñaValidada == true) {

				boolean cambioContraseña = usuario.setContraseña(nuevaContraseña);
				if (cambioContraseña == true)
					System.out.println("Contraseña cambiada con exito");
				else
					System.out.println("La contraseña no cumple las condiciones");

			}

		}
	}

	private static void agregarStockDeVacunas() {
		System.out.println("Nombre de la vacuna perteneciente al lote");
		String nombreLote = teclado.next();

		System.out.println("Ingrese nacionalidad de la vacuna");
		String nacionalidadVacuna = teclado.next();

		System.out.println("CUANTAS VACUNAS QUIERE AGREGAR AL STOCK ");
		int stockVacunas = teclado.nextInt();
		Vacuna nuevoStockVacuna = new Vacuna(nombreLote, nacionalidadVacuna, stockVacunas);
		Boolean registroDeStockVacunas = vacunate.agregarStockVacuna(stockVacunas);
		if (registroDeStockVacunas == true) {
			System.out.println("Vacunas agregadas con exito ");
		} else
			System.out.println("no se pudo agregar");

	}

	private static void asignarTurnoAdministrador() {
		System.out.println("INGRESE EL DNI DEL PACIENTE A VACUNAR");
		int dniPaciente = teclado.nextInt();
		Usuario pacienteAVacunar = vacunate.obtenerUsuarioPorDni(dniPaciente);

		if (pacienteAVacunar != null) {
			System.out.println("INGRESE EL DIA ");
			int diaAVacunar = teclado.nextInt();
			System.out.println("INGRESE MES A VACUNAR");
			String mesAVacunar = teclado.next();
			Turno turnoPaciente = new Turno(dniPaciente, diaAVacunar, mesAVacunar);
			boolean turnoAsignado = vacunate.asignarTurno(turnoPaciente);
			if (turnoAsignado == true) {
				System.out.println("TURNO ASIGNADO CORRECTAMENTE");
			} else {
				System.out.println("No se pudo asignar el turno ");
			}
		} else {
			System.out.println("No se encuentra un paciente con ese DNI");
		}

	}

	private static void registrarVacunaAdministrador() {
		System.out.println("INGRESE EL NOMBRE DE LA VACUNA");
		String nombreVacuna = teclado.next();
		System.out.println("INGRESE LA NACIONALIDAD DE LA VACUNA");
		String nacionalidadVacuna = teclado.next();

		Vacuna vacunaNueva = new Vacuna(nombreVacuna, nacionalidadVacuna, 1);
		boolean agregoVacuna = vacunate.agregarVacuna(vacunaNueva);
		if (agregoVacuna != false) {
			System.out.println(
					"VACUNA " + nombreVacuna + " de nacionalidad " + nacionalidadVacuna + " fue agregada con exito");
		} else {
			System.out.println("No se pudo agregar la vacuna");
		}
	}

	private static Usuario Loguear() {
		System.out.println("INGRESE SU DNI");
		int dniLogin = teclado.nextInt();

		System.out.println("INGRESE SU CONTRASEÑA");
		String contraseñaLogin = teclado.next();
		Usuario usuarioLogeado = vacunate.login(dniLogin, contraseñaLogin);

		if (usuarioLogeado != null) {
			System.out.println("Se logueo correctamente");
		} else {
			System.out.println("Usuario o password incorrecto");
		}
		return usuarioLogeado;
	}

	private static int menuAdministradorTurno() {
		System.out.println("1. PARA ASIGNAR UN TURNO ");
		System.out.println("2. PARA CANCERLAR UN TURNO ");
		int opciondeseadaTurno = teclado.nextInt();
		return opciondeseadaTurno;
	}

	private static void registrarVacunadorAdministrador() {
		System.out.println("INGRESE DOCUMENTO DEL VACUNADOR");
		int documentoVacunador = teclado.nextInt();
		System.out.println("INGRESE NOMBRE DEL VACUNADOR");
		String nombreVacunador = teclado.next();
		Rol rol = Rol.Paciente;

		for (int i = 0; i < 3; i++) {

			System.out.println("INGRESE CONTRASEÑA DEL USUARIO VACUNADOR");
			String contraseñaVacuandor = teclado.next();
			boolean contraseñaValidada = vacunate.validarContraseña(contraseñaVacuandor);

			if (contraseñaValidada == true) {
				Usuario usuario = new Usuario(documentoVacunador, nombreVacunador, contraseñaVacuandor, rol, null,
						null);

				Boolean registroDeUsuario = vacunate.registrarUsuario(usuario);
				if (registroDeUsuario == true) {
					System.out.println("Se creo un " + usuario.getRol());
					break;
				} else
					System.out.println("No sea ha registrado");

			} else
				System.out.println("La password no cumple las condicones");
		}
	}

	private static int menuVacunasAdministrador() {
		System.out.println("1. PARA REGISTRAR UN VACUNADOR");
		System.out.println("2. PARA AGREGAR LOTE DE VACUNA");
		System.out.println("3. PARA REGISTRAR UNA NUEVA VACUNA");
		int opcionDeseadaVacunas = teclado.nextInt();
		return opcionDeseadaVacunas;
	}

	private static int menuPrincipalAdministrador() {
		System.out.println("1. Para administrar Vacunas");
		System.out.println("2. Para administrar Turnos");
		int opcionDesada = teclado.nextInt();
		return opcionDesada;
	}

	private static void menuRegistrar() {
		boolean contraseñaValidada = false;
		System.out.println("INGRESE SU NUMERO DE DOCUMENTO");
		int documento = teclado.nextInt();

		System.out.println("INGRESE SU NOMBRE");
		String nombre = teclado.next();
		
		Rol rol = Rol.Paciente;
	
		for (int i = 0; i < 3; i++) {
			System.out.println("INGRESE SU CONTRASEÑA");
			String contraseña = teclado.next();
			contraseñaValidada = vacunate.validarContraseña(contraseña);

			if (contraseñaValidada == true) {
				Usuario usuario = new Usuario(documento, contraseña, nombre, rol, null, null);

				Boolean registroDeUsuario = vacunate.registrarUsuario(usuario);
				if (registroDeUsuario == true) {
					System.out.println("Se creo un " + usuario.getRol());
					break;
				} else
					System.out.println("No sea ha registrado");

			} else
				System.out.println("La password no cumple las condicones");
		}
	}

	private static int menuPrincipal() {
		int opcionDeseada;
		System.out.println("-------------------------------");
		System.out.println("1.REGISTRARTE COMO USUARIO.");
		System.out.println("2.PARA LOGUEARSE.");
		System.out.println("9.PARA SALIR.");
		System.out.println("-------------------------------");
		opcionDeseada = teclado.nextInt();
		return opcionDeseada;
	}

}
