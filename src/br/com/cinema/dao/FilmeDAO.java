package br.com.cinema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.cinema.connection.ConnectionFactory;
import br.com.cinema.modelo.Ator;
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
	
	public Filme getFilme(int id) {
		
		try(Connection c = new ConnectionFactory().getConnection()) {
			
			String sql = "select * from filme where id = ?;";
			
			Filme filme = null;
			
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				int idFilme = rs.getInt("id");
				String titulo = rs.getString("titulo");
				int duracao = rs.getInt("duracao");
				String genero = rs.getString("genero");
				
				filme = new Filme(titulo, duracao, genero);
				filme.setId(idFilme);
			}
			
			String sql2 = "select * from filme f join filme_ator fa on f.id = fa.id_filme join ator a on a.id = fa.id_ator where f.id = ?;";
			
			stmt = c.prepareStatement(sql2);
			stmt.setInt(1, id);
			
			rs = stmt.executeQuery();
			
			while(rs.next()){
				int idAtor = rs.getInt("a.id");
				String nomeAtor = rs.getString("a.nome");
				int idade = rs.getInt("a.idade");
				String generoAtor = rs.getString("a.genero");
				
				Ator ator = new Ator(nomeAtor, idade, generoAtor);
				ator.setId(idAtor);
				
				filme.adicionaAtor(ator);
			}
			
			return filme;
		} catch (SQLException e) {
			throw new RuntimeException(e);
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
