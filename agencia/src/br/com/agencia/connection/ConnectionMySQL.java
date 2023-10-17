package br.com.agencia.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionMySQL {
	private static final String USERNAME = "root";
	private static final String PASSWORD = "#Mila0497";
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/agencia";

	public static Connection creatConnectionToMySQL() throws Exception {
		// faz que a classe seja carregada pela JVM
		Class.forName("com.mysql.cj.jdbc.Driver");

		// cria conexão com bd
		Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);

		return connection;
		}
	
	public static void main(String[] args) throws Exception {
		// recuperar uma conexão com bd
		Connection con = creatConnectionToMySQL();

		// testar se a conexão é nula
		if (con != null) {
			System.out.println("Conexão obtida com sucesso");
			con.close();
		}
		}
	}
