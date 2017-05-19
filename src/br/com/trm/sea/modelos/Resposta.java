package br.com.trm.sea.modelos;

import br.com.trm.sistema.modelos.PojoPadrao;

public class Resposta extends PojoPadrao{
	
	private String resposta;
	private String pergunta;
	private int id_pergunta;
	private boolean correta;
	
	public String getResposta() {
		return resposta;
	}
	public String getPergunta() {
		return pergunta;
	}
	public boolean isCorreta() {
		return correta;
	}
	public void setResposta(String resposta) {
		this.resposta = resposta;
	}
	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}
	public void setCorreta(boolean correta) {
		this.correta = correta;
	}
	public int getId_pergunta() {
		return id_pergunta;
	}
	public void setId_pergunta(int id_pergunta) {
		this.id_pergunta = id_pergunta;
	}
}
