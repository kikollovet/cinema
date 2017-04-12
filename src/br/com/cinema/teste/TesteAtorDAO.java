package br.com.cinema.teste;

import java.sql.Connection;

import br.com.cinema.connection.ConnectionFactory;
import br.com.cinema.dao.AtorDAO;
import br.com.cinema.modelo.Ator;

public class TesteAtorDAO {

	public static void main(String[] args) {

		Connection connection = new ConnectionFactory().getConnection();
		
		Ator ator = new Ator("Jennifer Lawrence", 30, "F");
		
		AtorDAO atorDAO = new AtorDAO(connection);
		
		//atorDAO.adiciona(ator);
		
		//System.out.println(atorDAO.listaAtor());
		
		System.out.println(atorDAO.getAtor(2));
	}

}
