package br.com.cinema.teste;

import br.com.cinema.dao.AtorDAO;
import br.com.cinema.modelo.Ator;

public class TesteAtorDAO {

	public static void main(String[] args) {

		Ator ator = new Ator("Jennifer Lawrence", 30, "F");
		
		AtorDAO atorDAO = new AtorDAO();
		
		//atorDAO.adiciona(ator);
		
		//System.out.println(atorDAO.listaAtor());
		
		System.out.println(atorDAO.getAtor(2));
	}

}
