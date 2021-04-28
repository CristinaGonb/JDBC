/**
 * 
 */
package vista;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dao.*;

public class PrincipalEjercicio1 {

	private static Scanner teclado = new Scanner(System.in);

	public static void main(String[] args) {

		try {
			DaoPersona daoPersona = DaoPersona.getInstance();
			DaoCuenta daoCuenta = DaoCuenta.getInstance();
			int opcion;

			do {
				String dni, numCuenta;
				Persona personaEncontrada = null;

				menu();
				opcion = Integer.parseInt(teclado.nextLine());

				switch (opcion) {
				case 0:
					System.out.println("Cerrando sesión. Hasta la próxima");
					daoPersona.cerrarSesion();
					daoCuenta.cerrarSesion();
					break;
				case 1:
					System.out.println(daoPersona.listarPersonasConCuentaEnNumRojos());
					break;
				case 2:
					System.out.println(daoPersona.listadoPersonasSinCuenta());
					break;
				case 3:
					List<Persona> personasSinCuenta = daoPersona.listadoPersonasSinCuenta();
					if (personasSinCuenta != null && !personasSinCuenta.isEmpty()) {

						System.out.println("Persona con DNI: ");
						dni = teclado.nextLine();

				
						for (Persona personas : personasSinCuenta) {
							if (personas.getDni().equals(dni)) {
								personaEncontrada = personas;
							}
						}
						if (personaEncontrada != null) {
							System.out.println("Introduzca su cuenta: ");
							numCuenta = teclado.nextLine();
							Cuenta cuenta = new Cuenta(numCuenta, 100);
							daoCuenta.insertarCuenta(cuenta);
							// Añado la cuenta
							personaEncontrada.setCuenta(cuenta);
							// Actualizo la persona para añadir la cuenta
							daoPersona.actualizarPersona(personaEncontrada);
						}
					}
					break;
				}

			} while (opcion != 0);
		} catch (SQLException ex) {
			System.out.println("Error: " + ex.getMessage());
		}
	}

	public static void menu() {
		System.out.println("SISTEMA DE GESTIÓN DE EMPLEADOS");
		System.out.println("===============================");
		System.out.println("0. Salir");
		System.out.println("1. Listar todos las personas con cuenta en rojo");
		System.out.println("2. Listar todas las personas sin cuenta");
		System.out.println("3. Actualizar cuentas");
	}
}
