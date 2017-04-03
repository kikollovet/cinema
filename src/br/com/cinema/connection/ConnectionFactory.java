package br.com.cinema.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public Connection getConnection() {
		try{
			
			return DriverManager.getConnection("jdbc:mysql://localhost/cinema", "root", "kiko");
		
		} catch (SQLException e){
			e.printStackTrace();
			return null;
		}
		
	}
}
