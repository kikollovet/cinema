package br.com.cinema.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Filme {

	private int id;
	private String titulo;
	private int duracao;
	private String genero;
	private List<Ator> lista = new ArrayList<>();
	
	public Filme(){}

	public Filme(String titulo, int duracao, String genero) {
		this.titulo = titulo;
		this.duracao = duracao;
		this.genero = genero;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
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
	
	public List<Ator> getLista() {
		return Collections.unmodifiableList(this.lista);
	}
	
	public void adicionaAtor(Ator ator){
		this.lista.add(ator);
	}
	
	@Override
	public String toString() {
		return "Titulo: " + this.titulo + " Duracao: " + duracao + "m Genero: " + genero + "\nAtores: " + this.lista;
	}

}
