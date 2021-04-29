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
					Cuenta cuenta;
					List<Persona> personasSinCuenta = daoPersona.listadoPersonasSinCuenta();
					if (personasSinCuenta != null && !personasSinCuenta.isEmpty()) {

						for (Persona persona : personasSinCuenta) {
							System.out.println("Persona con DNI: " + persona.getDni());
							System.out.println("Introduzca su cuenta: ");
							numCuenta = teclado.nextLine();

							// Buscamos el idCuenta
							cuenta = daoCuenta.buscarCuentaPorNumeroCuenta(numCuenta);

							if (cuenta != null) { // lo hemos encontrado, actualizamos

								daoPersona.actualizarCuentaDePersona(persona.getDni(), cuenta.getIdCuenta());
								System.out.println("Cuenta actualizada con éxito");
								
							} else {
								System.out.println("No existe la cuenta " + numCuenta + " vamos a crearla");
								System.out.println("Dame el saldo");
								float saldo = (float) Double.parseDouble(teclado.nextLine());

								//Creamos la cuenta con los datos que hemos introducido
								daoCuenta.insertarCuenta(new Cuenta(numCuenta, saldo));
								//Añadimos la cuenta
								cuenta = daoCuenta.buscarCuentaPorNumeroCuenta(numCuenta);
								//Actualizamos la cuenta de la persona con los datos creados
								daoPersona.actualizarCuentaDePersona(persona.getDni(), cuenta.getIdCuenta());
								System.out.println("Cuenta creada con éxito");
							}
						}
					}
					break;
				case 4:
					break;
				case 5:
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
		System.out.println("4. Borrar personas con saldo negativo");
		System.out.println("5. Consultar el dni solicitando nombre y apellidos");
	}
}
