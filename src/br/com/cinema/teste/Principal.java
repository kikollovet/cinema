package br.com.cinema.teste;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

import br.com.cinema.modelo.Filme;

public class Principal {
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		
		Filme filme = new Filme("O lado bom da vida", 100, "Drama");
		
		Connection con = null;
		
		try{
			
			//DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con = DriverManager.getConnection("jdbc:mysql://localhost/cinema", "root", "kiko");
			
			String sql = "INSERT INTO FILME (titulo, duracao, genero) values (?,?,?);";
			
			PreparedStatement cmd = con.prepareStatement(sql);
			cmd.setString(1, filme.getTitulo());
			cmd.setInt(2, filme.getDuracao());
			cmd.setString(3, filme.getGenero());
			
			cmd.execute();
			cmd.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			con.close();
		}
	}
}
