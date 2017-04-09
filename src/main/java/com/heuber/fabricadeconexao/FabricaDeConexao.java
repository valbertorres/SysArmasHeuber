package com.heuber.fabricadeconexao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class FabricaDeConexao {

	private static String user = "";
	private static String password = "";
	private static String url = "jdbc:postgresql://localhost:5436/servico";
	private static String driver = "org.postgresql.Driver";

	public static synchronized Properties getPorpeties() {
		Properties properties = new Properties();
		FileInputStream file;
		try {
			file = new FileInputStream("C:\\BancoPropeties\\Banco.txt");
			properties.load(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return properties;
	}

	private FabricaDeConexao() {

	}

	private static FabricaDeConexao instancia;

	public static synchronized FabricaDeConexao getInstancia() {
		if (instancia == null)
			return instancia = new FabricaDeConexao();
		return instancia;
	}

	public Connection getConexao() throws ClassNotFoundException, SQLException {
		Properties properties = getPorpeties();
		this.user = properties.getProperty("User");
		this.password = properties.getProperty("Password");
		String Password = properties.getProperty("Password");
		String Servico = properties.getProperty("Servico");

		this.url = this.url.replace("servico", Servico);
		Class.forName(driver);
		Connection connection = DriverManager.getConnection(this.url, this.user, this.password);
		return connection;
	}

}
