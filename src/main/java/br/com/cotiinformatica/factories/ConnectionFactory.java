package br.com.cotiinformatica.factories;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

	private static String driver = "org.postgresql.Driver";
	private static String url = "jdbc:postgresql://postgres:5432/bd_apiClientes";
	private static String user = "postgres";
	private static String password = "241099";

	public static Connection getConnection() throws Exception {
		Class.forName(driver);
		return DriverManager.getConnection(url, user, password);
	}
}
