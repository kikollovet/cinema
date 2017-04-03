package br.com.cinema.teste;

import java.sql.SQLException;
import java.util.List;

import br.com.cinema.dao.FilmeDAO;
import br.com.cinema.modelo.Filme;

public class TesteFilmeDAO {

	public static void main(String[] args) throws SQLException {

		FilmeDAO dao = new FilmeDAO();
		
		List<Filme> lista = dao.listaFilme();
		
		System.out.println(lista);
	}

}
