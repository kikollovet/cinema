package br.com.cinema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.cinema.connection.ConnectionFactory;
import br.com.cinema.modelo.Filme;

public class FilmeDAO {

	public void adiciona(Filme filme) throws SQLException{
		
		Connection c = new ConnectionFactory().getConnection();
		
		String sql = "INSERT INTO FILME (titulo, duracao, genero) values (?,?,?);";
		
		try {
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setString(1, filme.getTitulo());
			stmt.setInt(2, filme.getDuracao());
			stmt.setString(3, filme.getGenero());
			
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			c.close();
		}
	}
	
	public List<Filme> listaFilme(){
		
		List<Filme> lista = new ArrayList<>();
		
		Connection c = new ConnectionFactory().getConnection();
		
		String sql = "SELECT * FROM FILME;";
		
		try {
			PreparedStatement stmt = c.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				String titulo = rs.getString("titulo");
				int duracao = rs.getInt("duracao");
				String genero = rs.getString("genero");
				
				Filme filme = new Filme(titulo, duracao, genero);
				
				lista.add(filme);
			}
			
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return lista;
	}
	
	public void apaga(int id){
		
		Connection c = new ConnectionFactory().getConnection();
		
		String sql = "DELETE FROM FILME WHERE id=?;";
		
		try {
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setInt(1, id);
			
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}
