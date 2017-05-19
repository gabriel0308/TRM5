package br.com.trm.sea.modelos;

import br.com.trm.sistema.modelos.PojoPadrao;

public class Pergunta extends PojoPadrao{
	
	private String pergunta;
	private int nivel;
	private int id_assunto;
	private String assunto;
	
	public String getPergunta() {
		return pergunta;
	}
	public String getAssunto() {
		return assunto;
	}
	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
	public int getNivel() {
		return nivel;
	}
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	public int getId_assunto() {
		return id_assunto;
	}
	public void setId_assunto(int id_assunto) {
		this.id_assunto = id_assunto;
	}
}
