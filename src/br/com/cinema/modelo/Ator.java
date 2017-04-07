package br.com.cinema.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ator {

	private int id;
	private String nome;
	private int idade;
	private String genero;
	private List<Filme> lista = new ArrayList<>();
	
	public Ator(String nome, int idade, String genero) {
		this.nome = nome;
		this.idade = idade;
		this.genero = genero;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
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
	
	public List<Filme> getLista() {
		return Collections.unmodifiableList(this.lista);
	}
	
	public void adicionaFilme(Filme filme){
		this.lista.add(filme);
	}
	
	@Override
	public String toString() {
		return "Nome: " + this.nome + " - Idade: " + this.idade + " - Gênero: " + this.genero + "\nFilmes: " + this.lista + "\n"; 
	}
}
