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


}
