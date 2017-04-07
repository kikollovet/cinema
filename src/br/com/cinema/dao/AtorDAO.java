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
	
	public Ator getAtor(int id) {
		
		try(Connection c = new ConnectionFactory().getConnection()){
			
			String sql = "select * from ator where id = ?;";
			
			Ator ator = null;
			
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()){
				int idAtor = rs.getInt("id");
				String nome = rs.getString("nome");
				int idade = rs.getInt("idade");
				String genero = rs.getString("genero");
				
				ator = new Ator(nome, idade, genero);
				ator.setId(idAtor);
			}
			
			String sql2 = "select * from filme f join filme_ator fa on f.id = fa.id_filme join ator a on a.id = fa.id_ator where a.id = ?;";
			
			stmt = c.prepareStatement(sql2);
			stmt.setInt(1, id);
			
			rs = stmt.executeQuery();
			
			while(rs.next()){
				int idFilme = rs.getInt("f.id");
				String titulo = rs.getString("f.titulo");
				int duracao = rs.getInt("f.duracao");
				String generoFilme = rs.getString("f.genero");
				
				Filme filme = new Filme(titulo, duracao, generoFilme);
				filme.setId(idFilme);
				
				ator.adicionaFilme(filme);
			}
			
			return ator;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
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
