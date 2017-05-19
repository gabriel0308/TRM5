package br.com.trm.sea.modelos;

import br.com.trm.sistema.modelos.PojoPadrao;

public class Questionario extends PojoPadrao{
	
	private String nome;
	private String tempoMin;
	private String tempoMax;
	private String perguntaMax;
	private String perguntaMaxAssunto;
	
	public String getNome() {
		return nome;
	}
	public String getTempoMin() {
		return tempoMin;
	}
	public String getTempoMax() {
		return tempoMax;
	}
	public String getPerguntaMax() {
		return perguntaMax;
	}
	public String getPerguntaMaxAssunto() {
		return perguntaMaxAssunto;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setTempoMin(String tempoMin) {
		this.tempoMin = tempoMin;
	}
	public void setTempoMax(String tempoMax) {
		this.tempoMax = tempoMax;
	}
	public void setPerguntaMax(String perguntaMax) {
		this.perguntaMax = perguntaMax;
	}
	public void setPerguntaMaxAssunto(String perguntaMaxAssunto) {
		this.perguntaMaxAssunto = perguntaMaxAssunto;
	}
}
