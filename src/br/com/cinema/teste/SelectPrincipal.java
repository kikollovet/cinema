package br.com.cinema.teste;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.cinema.modelo.Filme;

public class SelectPrincipal {

	public static void main(String[] args) throws SQLException {

		Connection con = null;
		
		List<Filme> filmes = new ArrayList<>();
		
		try{
			
			//DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con = DriverManager.getConnection("jdbc:mysql://localhost/cinema", "root", "kiko");
			
			String sql = "SELECT * FROM FILME";
			
			PreparedStatement cmd = con.prepareStatement(sql);
			
			ResultSet rs = cmd.executeQuery();
			
			while(rs.next()){
				String titulo = rs.getString("titulo");
				int duracao = rs.getInt("duracao");
				String genero = rs.getString("genero");
				
				Filme filme = new Filme(titulo, duracao, genero);
				
				filmes.add(filme);
			}
			
			
			cmd.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			con.close();
		}
		
		System.out.println(filmes);
	}

}
