package br.com.cinema.modelo;

public class Filme {

	private String titulo;
	private int duracao;
	private String genero;

	public Filme(String titulo, int duracao, String genero) {
		this.titulo = titulo;
		this.duracao = duracao;
		this.genero = genero;
	}

	public String getTitulo() {
		return titulo;
	}

	public int getDuracao() {
		return duracao;
	}

	public String getGenero() {
		return genero;
	}
	
	@Override
	public String toString() {
		return "Titulo: " + this.titulo + " Duracao: " + duracao + "m Genero: " + genero;
	}

}
