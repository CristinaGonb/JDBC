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

	/**
	 * Listado con todos los datos de las personas con cuenta en num rojos (-) Todos
	 * los datos de persona y cuenta
	 * 
	 * @throws SQLException
	 * @return result
	 */

	public List<Persona> listarPersonasConCuentaEnNumRojos() throws SQLException {
		// Si hay personas que tengan cuenta con numeros rojos, las añado a la lista
		List<Persona> result = new ArrayList<>();
		Cuenta cuenta;
		boolean hayDatos = false;

		try (PreparedStatement ps = con.prepareStatement(
				"SELECT * FROM persona as p INNER JOIN cuenta as c ON p.idcuenta= c.idcuenta WHERE c.saldo < 0 ");) {

			/*
			 * ps.setInt(1,100) posicion-valor Esto se utiliza en el caso de que la
			 * consulta, utilizara ? por cualquier numero/ string Se utilizaria en vez de
			 * executeQuery
			 */

			// Ejecuto la sentencia para obtener la informacion
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				// Si hay datos recogo la informacion de cada objeto
				hayDatos = true;

				// Creo objeto cuenta
				cuenta = new Cuenta(rs.getInt("c.idcuenta"), rs.getString("c.numerocuenta"), rs.getFloat("c.saldo"));
				// Añadimos la persona correspondiente a la lista
				result.add(new Persona(rs.getString("p.dni"), rs.getString("p.nombre"), rs.getString("p.apellidos"),
						cuenta));

				/*
				 * Otra forma de hacerlo Persona p= new Persona(rs.getString("p.dni"),
				 * rs.getString("p.nombre"), rs.getString("p.apellidos"));
				 *
				 */
			}
			// Cierro resultSet
			rs.close();
		}
		// Cuando no hay datos, finalizo
		if (!hayDatos) {
			result = null;
		}

		return result;
	}

	/**
	 * Listado de todos los datos de las personas que no tienen cuenta
	 * @return result
	 * @throws SQLException 
	 */

	public List<Persona> listadoPersonasSinCuenta() throws SQLException {
		List<Persona> result = new ArrayList<Persona>();
		boolean hayDatos=false;
		Cuenta cuenta;
		
		try (PreparedStatement ps = con.prepareStatement("SELECT * FROM persona WHERE idcuenta IS null");) {
			// Ejecuto la sentencia para obtener la informacion
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				// Si hay datos recogo la informacion de cada objeto
				hayDatos = true;

				// Añadimos la persona correspondiente a la lista
				result.add(new Persona(rs.getString("dni"), rs.getString("nombre"), rs.getString("apellidos")));

			}
			// Cierro resultSet
			rs.close();
		}
		// Cuando no hay datos, finalizo
		if (!hayDatos) {
			result = null;
		}

		return result;
	}
	/**
	 * Actualizar la cuenta de una persona
	 * @throws SQLException 
	 */
	public void actualizarPersona(Persona p) throws SQLException {
		try(PreparedStatement ps= con.prepareStatement("UPDATE persona SET numerocuenta=?,saldo=?");){
			
			ps.setObject(1,p.getCuenta());
			ps.setObject(2,p.getCuenta());
			
			ps.executeUpdate();
		}
	}

	public void actualizarCuentaDePersona(String dni, int idCuenta) throws SQLException {
		try(PreparedStatement ps= con.prepareStatement("UPDATE persona SET idcuenta=? where dni=?");){
			ps.setInt(1, idCuenta);
			ps.setString(2, dni);
			
			ps.executeUpdate();
		}
		
	}
}
