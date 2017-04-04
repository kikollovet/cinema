package br.com.cinema.modelo;

public class Ator {

	private String nome;
	private int idade;
	private String genero;

	public Ator(String nome, int idade, String genero) {
		this.nome = nome;
		this.idade = idade;
		this.genero = genero;
	}

	public String getNome() {
		return nome;
	}

	public int getIdade() {
		return idade;
	}

	public String getGenero() {
		return genero;
	}
	
	@Override
	public String toString() {
		return "Nome: " + this.nome + " - Idade: " + this.idade + " - Gênero: " + this.genero;
	}
}
