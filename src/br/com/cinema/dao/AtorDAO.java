package br.com.cinema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.cinema.connection.ConnectionFactory;
import br.com.cinema.modelo.Ator;

public class AtorDAO {

	public void adiciona(Ator ator){
		
		try(Connection c = new ConnectionFactory().getConnection()){
			
			String sql = "INSERT INTO ATOR (nome, idade, genero) values (?,?,?);";
			
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setString(1, ator.getNome());
			stmt.setInt(2, ator.getIdade());
			stmt.setString(3, ator.getGenero());
			
			stmt.execute();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void getAtor(int id) {
		
	}
	
	public List<Ator> listaAtor() {
		
		List<Ator> lista = new ArrayList<>();
		
		try (Connection c = new ConnectionFactory().getConnection()){
			
			String sql = "SELECT * FROM ATOR;";
			
			PreparedStatement stmt = c.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				
				String nome = rs.getString("nome");
				int idade = rs.getInt("idade");
				String genero = rs.getString("genero");
				
				Ator ator = new Ator(nome, idade, genero);
				
				lista.add(ator);
			}
			return lista;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
