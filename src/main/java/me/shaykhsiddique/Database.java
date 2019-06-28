package me.shaykhsiddique;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	private String database_name = "onlinejudge2019"; 
	private String host = "jdbc:mysql://localhost:3306/";
	private String user = "shaykh";
	private String password = "147461";
	public Connection JdbcConfig() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(this.host+this.database_name, this.user, this.password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	
	}
	public Database() {
		super();
	}
}
