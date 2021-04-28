package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import singleton.DBConnection;

public class DaoCuenta {

	/*
	 * PROPIEDADES Y M�TODOS SINGLETON
	 */

	private Connection con = null;

	private static DaoCuenta instance = null;// obj misma clase

	private DaoCuenta() throws SQLException {
		con = DBConnection.getConnection();// conexion
	}

	public static DaoCuenta getInstance() throws SQLException {
		if (instance == null)
			instance = new DaoCuenta();// se crea cuando lo llamo por 1 vez

		return instance;
	}

	public void cerrarSesion() throws SQLException {
		con.close();

	}

	/**
	 * Crear cuenta para las personas que no tienen.
	 * @throws SQLException 
	 */
	public void insertarCuenta(Cuenta c) throws SQLException {
		
		//Creo consulta para añadir una cuenta nueva
		try(PreparedStatement ps= con.prepareStatement("INSERT INTO cuenta (numerocuenta,saldo) VALUES (?,?)");){
			//El id es autoincrement, por lo que no se añade porque aparece por defecto
			ps.setString(1, c.getNumeroCuenta());
			ps.setFloat(2, c.getSaldo());
			
			//Actualizar sentencia
			ps.executeUpdate();
			
		}
	}
}
