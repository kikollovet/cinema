package br.com.cinema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.cinema.connection.ConnectionFactory;
import br.com.cinema.modelo.Ator;
import br.com.cinema.modelo.Filme;

public class FilmeDAO {

	private Connection connection;
	
	public FilmeDAO(Connection connection){
		this.connection = connection;
	}
	
	public void adiciona(Filme filme) throws SQLException{
		
		String sql = "INSERT INTO FILME (titulo, duracao, genero) values (?,?,?);";
		
		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, filme.getTitulo());
			stmt.setInt(2, filme.getDuracao());
			stmt.setString(3, filme.getGenero());
			
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	public Filme getFilme(int id) {
		
		try {
			
			String sql = "select * from filme where id = ?;";
			
			Filme filme = null;
			
			PreparedStatement stmt = this.connection.prepareStatement(sql);
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
			
			stmt = this.connection.prepareStatement(sql2);
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
		
		String sql = "SELECT * FROM FILME;";
		
		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			
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
		} 
		
		return lista;
	}
	
	public void apaga(int id){
		
		String sql = "DELETE FROM FILME WHERE id=?;";
		
		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setInt(1, id);
			
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void adicionaFilmeAtor(Filme filme) {
		
		try {
			
			int idFilme = 0;
			
			String sql = "INSERT INTO FILME (titulo, duracao, genero) values (?,?,?);";
			
			PreparedStatement stmt = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, filme.getTitulo());
			stmt.setInt(2, filme.getDuracao());
			stmt.setString(3, filme.getGenero());
			stmt.execute();
			
			ResultSet rs = stmt.getGeneratedKeys();
			
			if(rs.next()){
				idFilme = rs.getInt(1);
			}
			
			for (Ator ator : filme.getLista()) {
				int idAtor = 0;
				
				String sql2 = "INSERT INTO ATOR (nome, idade, genero) values (?,?,?);";
				
				stmt = this.connection.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
				stmt.setString(1, ator.getNome());
				stmt.setInt(2, ator.getIdade());
				stmt.setString(3, ator.getGenero());
				stmt.execute();
				
				rs = stmt.getGeneratedKeys();
				
				if(rs.next()){
					idAtor = rs.getInt(1);
				}
				
				String sql3 = "INSERT INTO filme_ator (id_filme, id_ator) values (?,?);";
				
				stmt = this.connection.prepareStatement(sql3);
				stmt.setInt(1, idFilme);
				stmt.setInt(2, idAtor);
				stmt.execute();
			}
					
			
		} catch(SQLException e){
			throw new RuntimeException(e);
		}
		
	}
}
