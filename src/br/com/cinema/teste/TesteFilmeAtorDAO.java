package br.com.cinema.teste;

import br.com.cinema.dao.FilmeDAO;
import br.com.cinema.modelo.Ator;
import br.com.cinema.modelo.Filme;

public class TesteFilmeAtorDAO {

	public static void main(String[] args) {

		Filme filme = new Filme("T2 Trainspotting", 117, "Drama/Comédia");
		
		Ator ewan = new Ator("Ewan MacGregor", 46, "M");
		Ator jonny = new Ator("Jonny Lee Miller", 44, "M");
		Ator robert = new Ator("Robert Carlyle", 55, "M");
		Ator ewen = new Ator("Ewen Bremmer", 45, "M");
		
		filme.adicionaAtor(ewen);
		filme.adicionaAtor(jonny);
		filme.adicionaAtor(robert);
		filme.adicionaAtor(ewan);
		
		FilmeDAO dao = new FilmeDAO();
		dao.adicionaFilmeAtor(filme);
		
	}

}
