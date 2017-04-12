package br.com.cinema.teste;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.cinema.connection.ConnectionFactory;
import br.com.cinema.dao.FilmeDAO;
import br.com.cinema.modelo.Filme;

public class TesteFilmeDAO {

	public static void main(String[] args) throws SQLException {

		Connection connection = new ConnectionFactory().getConnection();
		
		FilmeDAO dao = new FilmeDAO(connection);
		
		//List<Filme> lista = dao.listaFilme();
		
		//dao.apaga(2);
		
		System.out.println(dao.getFilme(3));
	}

}
