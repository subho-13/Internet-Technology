package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connector {
	private final String url;
	private final Properties properties = new Properties();

	public Connector(String url, String user, String password) {
		this.url = url;
		this.properties.setProperty("user", user);
		this.properties.setProperty("password", password);
	}

	public Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("org.postgresql.Driver");
		return DriverManager.getConnection(this.url, this.properties);
	}
}
