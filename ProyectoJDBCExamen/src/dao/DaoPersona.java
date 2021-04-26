package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import singleton.DBConnection;

public class DaoPersona {
	/*
	 * PROPIEDADES Y M�TODOS SINGLETON
	 */

	private Connection con = null;

	private static DaoPersona instance = null;

	private DaoPersona() throws SQLException {
		con = DBConnection.getConnection();
	}

	public static DaoPersona getInstance() throws SQLException {
		if (instance == null)
			instance = new DaoPersona();

		return instance;
	}
	
	public void cerrarSesion() throws SQLException {
		con.close();

	}
	
	/*
	 * Listado con todos los datos de las personas con cuenta en num rojos (-) Todos
	 * los datos de persona y cuenta
	 */

	public List<Persona> listarPersonasConCuentaEnNumRojos() throws SQLException {
		// Si hay personas que tengan cuenta con numeros rojos, las añado a la lista
		List<Persona> result = new ArrayList<>();
		Cuenta cuenta;
		boolean hayDatos = false;

		try (PreparedStatement ps = con.prepareStatement(
				"SELECT p.nombre,p.idcuenta,c.saldo FROM persona as p INNER JOIN cuenta as c ON p.idcuenta= c.idcuenta WHERE c.saldo < 0 ");) {

			// Ejecuto la sentencia para obtener la informacion
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				// Si hay datos recogo la informacion de cada objeto
				hayDatos = true;
				

			//	cuenta = new Cuenta(rs.getInt("c.idcuenta"), rs.getString("c.numerocuenta"), rs.getFloat("c.saldo"));

				//result.add(new Persona(rs.getString("p.dni"), rs.getString("p.nombre"), rs.getString("p.apellidos"),
						//cuenta));
			}
			rs.close();
		}
		// Cuando no hay datos, finalizo
		if (!hayDatos) {
			result = null;
		}

		return result;
	}
	

}
