/**
 * 
 */
package vista;

import java.sql.SQLException;

import java.util.Scanner;

import dao.*;


public class PrincipalEjercicio1 {

	private static Scanner teclado=new Scanner(System.in);

	public static void main(String[] args) {

		try {
			DaoPersona persona= DaoPersona.getInstance();
			DaoCuenta cuenta=DaoCuenta.getInstance();
			int opcion;
			
			do {
				menu();
				opcion = Integer.parseInt(teclado.nextLine());
				
				switch (opcion) {
				case 0:
					persona.cerrarSesion();
					cuenta.cerrarSesion();
					break;
				case 1:
				persona.listarPersonasConCuentaEnNumRojos();
					break;
				case 2:
					//
					break;
				case 3:
					//
					break;
				

				}

			} while (opcion != 0);
		} catch (SQLException ex) {
			System.out.println("Error: " + ex.getMessage());
		}

	}


	public static void menu() {

		System.out.println("SISTEMA DE GESTI�N DE EMPLEADOS");
		System.out.println("===============================");
		System.out.println("0. Salir");
		System.out.println("1. Listar todos las personas con cuenta en rojo");
		System.out.println("2. Listar todas las personas sin cuenta");
		System.out.println("3. Actualizar cuentas");
	

	}
	
	



	
}
